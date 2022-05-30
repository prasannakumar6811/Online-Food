package onlinefood.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import onlinefood.model.UserFoodOrder;

public interface UserFoodOrderDao extends JpaRepository<UserFoodOrder, Integer> {

}
