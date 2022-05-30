package onlinefood.service;

import java.util.List;

import onlinefood.model.Food;

public interface IFoodService {
	public Food addFood(Food food);
	public Food updateFood(Food food);
	public Food printFood(String foodName);
	public Food deleteFood(Food food);
	public List<Food> printAllFood();
	public List<Food> getFoodByPrice(double foodPrice);
	public List<Food>  getFoodByType(String foodType);

}
