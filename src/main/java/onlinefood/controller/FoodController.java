package onlinefood.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import onlinefood.model.Admin;
import onlinefood.model.Food;
import onlinefood.service.FoodService;

@RestController
public class FoodController {
	@Autowired
	private FoodService ser;

	Logger logger = LoggerFactory.getLogger(FoodController.class);

	@PostMapping("addfood")
	public ResponseEntity<Food> addFood(@Valid @RequestBody Food food,HttpSession session) {
		Admin admin = (Admin) session.getAttribute("admin");
		Food fd = null;
		if(admin!=null)
		{
			fd = ser.addFood(food);
			logger.info("food item inserted");
			return new ResponseEntity<Food>(fd, HttpStatus.ACCEPTED);
		}
		else
		{
			return new ResponseEntity<Food>(fd, HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("updateFood")
	public ResponseEntity<Food> updateFood(@Valid @RequestBody Food food) {
		logger.info("food item updated");
		return new ResponseEntity<Food>(ser.updateFood(food), HttpStatus.OK);
	}

	@GetMapping("getFood/{foodName}")
	public ResponseEntity<Food> getFood(@PathVariable("foodName") String foodName) {
		Food ft = ser.printFood(foodName);
		if (ft != null) {
			logger.info("food found");
			return new ResponseEntity<Food>(ft, HttpStatus.OK);
		} else {
			logger.info("wrong food item selected");
			return new ResponseEntity<Food>(ft, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("deletefood/{foodName}")
	public ResponseEntity<Food> deleteFood(@Valid @RequestBody Food food) {
		logger.info("food itiam deleted");
		return new ResponseEntity<Food>(ser.deleteFood(food), HttpStatus.OK);
	}

	@GetMapping("getAllfood")
	public ResponseEntity<List<Food>> getAllfoodItiem() {
		logger.info("getting all food itiem");
		return ResponseEntity.ok(ser.printAllFood());
	}

	@GetMapping("getFoodByPrice/{foodPrice}")
	public ResponseEntity<List<Food>> getFoodByPrice(Food food) {
		logger.info("getting food by price");
		return ResponseEntity.ok(ser.getFoodByPrice(food.getFoodPrice()));
	}

	@GetMapping("getFoodByType/{foodType}")
	public ResponseEntity<List<Food>> getFoodByType(Food food) {
		logger.info("getting food by type");
		return ResponseEntity.ok(ser.getFoodByType(food.getFoodType()));
	}
}
