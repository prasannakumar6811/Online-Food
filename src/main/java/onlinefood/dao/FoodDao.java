package onlinefood.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import onlinefood.model.Food;

public interface FoodDao extends JpaRepository<Food,Integer> {
		public Food findByFoodName(String foodName); 
		public List<Food> findByFoodPrice(double foodPrice);
		public List<Food>  findByFoodType(String foodType);
}
