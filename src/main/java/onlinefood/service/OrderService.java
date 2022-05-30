package onlinefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlinefood.dao.OrderDao;
import onlinefood.exception.EmptyListException;
import onlinefood.exception.NoSuchOrderIdException;
import onlinefood.model.OrderDetails;

@Service
public class OrderService implements IOrderService {
	@Autowired
	private OrderDao dao;

	@Override
	public OrderDetails addOrder(OrderDetails order) {
		return dao.save(order);
	}

	@Override
	public OrderDetails getOrder(long orderId) {
		if (orderId != 0) {
			return dao.findByOrderId(orderId);
		} else {
			throw new NoSuchOrderIdException("307","No Such OrderId exists");
		}
	}
	
	@Override
	public List<OrderDetails> getAllOrders() {
		List<OrderDetails> lst = dao.findAll();
		if(lst.isEmpty())
		{
			throw new EmptyListException("610","List is empty");
		}
		return lst;
	}

	@Override
	public OrderDetails deleteOrder(long orderId) {
		OrderDetails od = dao.findByOrderId(orderId);
		dao.delete(od);
		if (od != null) {
			return od;
		} else {
			throw new NoSuchOrderIdException("307","No Such OrderId exists");
		}
	}

}
