package onlinefood.service;

import onlinefood.model.Admin;

public interface IAdminService {
	public Admin adminSignUp(Admin admin);
	public Admin adminLogin(String emailId, String Password);
	public Admin getAdmin(String emailId);
	public Admin updateAdmin(Admin admin);
	public Admin deleteAdmin(Admin admin);
}
