package onlinefood.service;

import onlinefood.model.User;

public interface IUserService {
	public User login(String email,String password);
	public User createLogin(User log);
	public User PrintUser(int id);
	public User updateLogin(User user);
	public User deleteLogin(String email);
}
