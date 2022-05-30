package onlinefood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlinefood.dao.AdminDao;
import onlinefood.exception.EmptyInputException;
import onlinefood.exception.NoSuchAdminException;
import onlinefood.exception.WrongPasswordException;
import onlinefood.model.Admin;

@Service
public class AdminService implements IAdminService {
	@Autowired
	private AdminDao dao;

	@Override
	public Admin adminSignUp(Admin admin) {
		if (admin.getName().isEmpty() || admin.getName().length() == 0) {
			throw new EmptyInputException("515", "Input Fields Can't be Empty");
		}
		return dao.save(admin);
	}

	@Override
	public Admin adminLogin(String emailId, String Password) {
		Admin admin = dao.findByEmailId(emailId);
		if (admin != null) {
			if (admin.getPassword().equals(Password)) {
				return admin;
			} else {
				throw new WrongPasswordException("670", "Incorrect Password");
			}
		} else {
			throw new NoSuchAdminException("675", "No Such Admin Exists");
		}
	}

	@Override
	public Admin getAdmin(String emailId) {
		Admin ad = dao.findByEmailId(emailId);
		if (ad != null) {
			return ad;
		} else {
			throw new NoSuchAdminException("675", "No Such Admin Exists");
		}
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		Admin ad = dao.findByEmailId(admin.getEmailId());
		if (ad != null) {
			ad.setMobileNumber(admin.getMobileNumber());
			ad.setName(admin.getName());
			ad.setPassword(admin.getPassword());
			return dao.save(ad);
		} else {
			throw new NoSuchAdminException("675", "No Such Admin Exists");
		}
	}

	@Override
	public Admin deleteAdmin(String emailId) {
		Admin ad = dao.findByEmailId(emailId);
		if (ad != null) {
			dao.delete(ad);
			return ad;
		} else {
			throw new NoSuchAdminException("675", "No Such Admin Exists");
		}
	}
}
