package onlinefood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlinefood.dao.UserDao;
import onlinefood.exception.EmptyInputException;
import onlinefood.exception.NoSuchUserException;
import onlinefood.exception.WrongPasswordException;
import onlinefood.model.User;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserDao dao;

	@Override
	public User login(String email, String password) {
		User u = dao.findByEmailId(email);
		if (u != null) {
			if (u.getPassword().equals(password)) {
				return u;
			} else {
				throw new WrongPasswordException("607", "Incorrect Password");
			}
		} else {
			throw new NoSuchUserException("605", "No Such User Exists");
		}
	}

	@Override
	public User createLogin(User log) {
		if (log.getEmailId().isEmpty() || log.getEmailId().length() == 0) {
			throw new EmptyInputException("601", "Input Field can't be Empty");
		}
		return dao.save(log);
	}

	@Override
	public User updateLogin(User user) {
		User use = dao.findByEmailId(user.getEmailId());
		if (use != null) {
			use.setName(user.getName());
			use.setAddress(user.getAddress());
			use.setPhoneNumber(user.getPhoneNumber());
			use.setPassword(user.getPassword());
			return dao.save(use);
		} else {
			throw new NoSuchUserException("605", "No Such User Exists");
		}
	}

	@Override
	public User deleteLogin(String email) {
		User user = dao.findByEmailId(email);
		if (user != null) {
			dao.delete(user);
			return user;
		} else {
			throw new NoSuchUserException("605", "No Such User Exists");
		}
	}

	@Override
	public User PrintUser(int id) {
		User user = dao.findById(id).get();
		if (user != null) {
			return user;
		} else {
			throw new NoSuchUserException("605", "No Such User Exists");
		}
	}

}
