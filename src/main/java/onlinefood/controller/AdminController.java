package onlinefood.controller;

import javax.servlet.http.HttpServletRequest;
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

import onlinefood.model.Admin;
import onlinefood.service.AdminService;

@RestController
public class AdminController {
	@Autowired
	private AdminService service;

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@PostMapping("/adminSignup")
	public ResponseEntity<Admin> adminSignup(@Valid @RequestBody Admin admin) {
		Admin ad = service.adminSignUp(admin);
		logger.info("Admin Signup is Successfull");
		return new ResponseEntity<Admin>(ad, HttpStatus.CREATED);
	}

	@GetMapping("/adminlogin/{emailId}/{password}")
	public ResponseEntity<Admin> getLogin(@Valid @PathVariable("emailId") String emailId,
			@PathVariable("password") String password,HttpServletRequest req) {
		Admin admin = service.adminLogin(emailId, password);
		if (admin.getEmailId().equals(emailId) && admin.getPassword().equals(password)) {
			req.getSession().setAttribute("admin", admin);
			logger.info("admin login successful with the emailId " + emailId);
			return new ResponseEntity<Admin>(admin, HttpStatus.ACCEPTED);
		} else {
			logger.warn("admin login Failed with the emailId " + emailId);
			return new ResponseEntity<Admin>(admin, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAdminDetails/{emailId}")
	public ResponseEntity<Admin> getAdminDetails(@Valid @PathVariable("emailId") String emailId) {
		Admin ad = service.getAdmin(emailId);
		if (ad.getEmailId().equals(emailId)) {
			logger.info("admin details found with the given emailId-" + emailId);
			return new ResponseEntity<Admin>(ad, HttpStatus.FOUND);
		} else {
			logger.warn("admin details not found with the given emailId-" + emailId);
			return new ResponseEntity<Admin>(ad, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/updateAdmin")
	public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin) {
		Admin ad = service.updateAdmin(admin);
		logger.info("admin Details are updated");
		return new ResponseEntity<Admin>(ad, HttpStatus.OK);
	}

	@DeleteMapping("/deleteAdmin/{emailId}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable("emailId") String emailId) {
		Admin ad = service.deleteAdmin(emailId);
		if (ad.getEmailId().equals(emailId)) {
			logger.info("Admin Details deleted with the given emailId-" + emailId);
			return new ResponseEntity<Admin>(ad, HttpStatus.GONE);
		} else {
			logger.warn("Admin Details not deleted with the given emailId-" + emailId);
			return new ResponseEntity<Admin>(ad, HttpStatus.NOT_FOUND);
		}
	}
}
