package onlinefood.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import onlinefood.model.User;

public interface UserDao extends JpaRepository<User, Integer> {
	public  User findByEmailId(String email);
}
