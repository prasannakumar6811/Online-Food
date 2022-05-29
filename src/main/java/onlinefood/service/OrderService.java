package onlinefood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlinefood.dao.OrderDao;
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
			return new OrderDetails();
		}
	}

	@Override
	public OrderDetails deleteOrder(long orderId) {
		OrderDetails od = dao.findByOrderId(orderId);
		dao.delete(od);
		if (od != null) {
			return od;
		} else {
			return null;
		}
	}

}
