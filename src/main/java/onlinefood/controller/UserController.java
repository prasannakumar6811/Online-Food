package onlinefood.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import onlinefood.model.User;
import onlinefood.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService ser;

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/usersignup")
	public ResponseEntity<User> userSignup(@Valid @RequestBody User user) {
		User us = ser.createLogin(user);
		logger.info("User Signup is Successfull");
		return new ResponseEntity<User>(us, HttpStatus.ACCEPTED);

	}

	@GetMapping("userlogin/{emailId}/{password}")
	public ResponseEntity<User> getLogin(@Valid @PathVariable("emailId") String email,
			@PathVariable("password") String password, HttpServletRequest req) {

		User user = ser.login(email, password);
		req.getSession().setAttribute("user", user);
		logger.info("login is sucessfully");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@GetMapping("printUser/{id}")
	public ResponseEntity<User> getUserDetails(@Valid @PathVariable("id") int id) {

		User user = ser.PrintUser(id);
		if (user != null) {
			logger.info("User Found with the Id" + id);
			return new ResponseEntity<User>(user, HttpStatus.FOUND);
		} else {
			logger.warn("User Not Found with the Id" + id);
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "updateUser")
	public ResponseEntity<User> getupdate(@Valid @RequestBody User user) {
		logger.info("update successfull with the Id"+user.getEmailId());
		return new ResponseEntity<User>(ser.updateLogin(user), HttpStatus.OK);
	}

	@DeleteMapping("deleteUser")
	public ResponseEntity<User> delLogin(HttpSession session) {
		User user = (User) session.getAttribute("user");
		User u = ser.deleteLogin(user.getEmailId());
		if (u != null) {
			logger.info("account deleted");
			return new ResponseEntity<User>(u, HttpStatus.OK);
		} else {
			logger.info("account not deleted");
			return new ResponseEntity<User>(u, HttpStatus.NOT_FOUND);
		}
	}
}
