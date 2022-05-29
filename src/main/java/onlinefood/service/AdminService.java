package onlinefood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlinefood.dao.AdminDao;
import onlinefood.exception.EmptyInputException;
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
		Admin admin = dao.findByEmailIdAndPassword(emailId, Password);
		if(admin != null) 
		{
			return admin;
		} 
		else 
		{
			return new Admin();
		}
	}

	@Override
	public Admin getAdmin(String emailId) {
		if(emailId!= null) 
		{
			return dao.findByEmailId(emailId);
		} 
		else 
		{
			return new Admin();
		}
	}

	@Override
	public Admin updateAdmin(Admin admin) {
		Admin ad = dao.findByEmailId(admin.getEmailId());
		ad.setMobileNumber(admin.getMobileNumber());
		ad.setName(admin.getName());
		ad.setPassword(admin.getPassword());
		return dao.save(ad);
	}

	@Override
	public Admin deleteAdmin(Admin admin) {
		Admin ad=dao.findByEmailId(admin.getEmailId());
		dao.delete(ad);
		if(ad!= null) 
		{
			return ad;
		} 
		else 
		{
			return null;
		}
	}
}
