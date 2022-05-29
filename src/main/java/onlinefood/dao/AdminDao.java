package onlinefood.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import onlinefood.model.Admin;

public interface AdminDao extends JpaRepository<Admin, String> {
	public Admin findByEmailIdAndPassword(String emailId, String password);
    public Admin findByEmailId(String emailId);
}
