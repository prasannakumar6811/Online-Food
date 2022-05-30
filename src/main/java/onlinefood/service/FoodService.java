package onlinefood.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlinefood.dao.FoodDao;
import onlinefood.exception.EmptyInputException;
import onlinefood.exception.NoSuchFoodItemException;
import onlinefood.exception.NoSuchFoodTypeException;
import onlinefood.model.Food;

@Service
public class FoodService implements IFoodService {
	@Autowired
	private FoodDao dao;

	@Override
	public Food addFood(Food food) {
		if (food.getFoodName().isEmpty() || food.getFoodName().length() == 0) {
			throw new EmptyInputException("515", "Input Fields Can't be Empty");
		}
		return dao.save(food);
	}

	@Override
	public Food updateFood(Food food) {
		Food food1 = dao.findByFoodName(food.getFoodName());
		if (food1 != null) {
			food1.setFoodPrice(food.getFoodPrice());
		} else {
			throw new NoSuchFoodItemException("604", "No Such FoodItem exists");
		}
		return dao.save(food1);
	}

	@Override
	public Food printFood(String foodName) {
		Food f = dao.findByFoodName(foodName);
		if (f != null) {
			return f;
		} else {
			throw new NoSuchFoodItemException("604", "No Such FoodItem exists");
		}
	}

	@Override
	public Food deleteFood(Food food) {
		Food food1 = dao.findByFoodName(food.getFoodName());
		dao.delete(food1);
		if (food1 != null) {
			return food1;
		} else {
			throw new NoSuchFoodItemException("604", "No Such FoodItem exists");
		}

	}

	@Override
	public List<Food> getFoodByPrice(double foodPrice) {

		return dao.findByFoodPrice(foodPrice);
	}

	@Override
	public List<Food> getFoodByType(String foodType) {
		List<Food> lfood = dao.findByFoodType(foodType);
		if(lfood!=null)
		{
			return lfood;
		}
		else
		{
			throw new NoSuchFoodTypeException("561","No Such Food Type Exists");
		}
	}

	@Override
	public List<Food> printAllFood() {
		Iterable<Food> itr = dao.findAll();
		List<Food> t = new ArrayList<Food>();
		for (Food ft : itr) {
			t.add(ft);
		}
		return t;

	}

}
