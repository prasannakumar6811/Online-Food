package onlinefood.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import onlinefood.dao.UserFoodOrderDao;
import onlinefood.model.Food;
import onlinefood.model.OrderDetails;
import onlinefood.model.User;
import onlinefood.model.UserFoodOrder;
import onlinefood.service.FoodService;
import onlinefood.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	private OrderService service;

	@Autowired
	private FoodService foodser;

	@Autowired
	private UserFoodOrderDao uod;

	Logger logger = LoggerFactory.getLogger(OrderController.class);

	@GetMapping("/getOrderDetails/{orderId}")
	public ResponseEntity<OrderDetails> getOrderDetails(@Valid @PathVariable("orderId") long orderId) {
		OrderDetails od = service.getOrder(orderId);
		if (od.getOrderId() == orderId) {
			logger.info("Order details found with the given orderId-" + orderId);
			return new ResponseEntity<OrderDetails>(od, HttpStatus.FOUND);
		} else {
			logger.warn("Order details not found with the given orderId-" + orderId);
			return new ResponseEntity<OrderDetails>(od, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getAllOrderDetails")
	public ResponseEntity<List<OrderDetails>> getAllOrderDetails() {
		List<OrderDetails> od = service.getAllOrders();
		if (od.isEmpty()) {
			logger.info("Order details not found");
			return new ResponseEntity<List<OrderDetails>>(od, HttpStatus.NOT_FOUND);
		} else {
			logger.warn("Order details found");
			return new ResponseEntity<List<OrderDetails>>(od, HttpStatus.FOUND);
		}
	}

	@DeleteMapping("/deleteOrder/{orderId}")
	public ResponseEntity<OrderDetails> deleteOrder(@Valid @PathVariable("orderId") long orderId) {
		OrderDetails od = service.deleteOrder(orderId);
		if (od.getOrderId() == orderId) {
			logger.info("Order Details deleted with the given orderId-" + orderId);
			return new ResponseEntity<OrderDetails>(od, HttpStatus.GONE);
		} else {
			logger.warn("Order Details not deleted with the given orderId-" + orderId);
			return new ResponseEntity<OrderDetails>(od, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/GenerateOrderId")
	public ResponseEntity<OrderDetails> addOrder(@RequestBody OrderDetails order, HttpServletRequest req) {
		User us = (User) req.getSession().getAttribute("user");
		us.getOrder().add(order);
		order.setUser(us);
		OrderDetails o = service.addOrder(order);
		req.getSession().setAttribute("orderId", o);
		if (o != null) {
			return new ResponseEntity<OrderDetails>(order, HttpStatus.OK);
		} else {
			return new ResponseEntity<OrderDetails>(order, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/placeFood")
	public ResponseEntity<UserFoodOrder> OrderPlace(@RequestBody UserFoodOrder ufo, HttpServletRequest req) {
		OrderDetails us = (OrderDetails) req.getSession().getAttribute("orderId");
		Food f2 = foodser.printFood(ufo.getFoodName());
		ufo.setFoodId(f2.getFoodId());
		ufo.setFoodPrice(f2.getFoodPrice());
		ufo.setFoodType(f2.getFoodType());
		us.getFood().add(ufo);
		ufo.setOrder(us);
		uod.save(ufo);

		return new ResponseEntity<UserFoodOrder>(ufo, HttpStatus.ACCEPTED);
	}
}
