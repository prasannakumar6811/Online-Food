package onlinefood.service;

import java.util.List;

import onlinefood.model.OrderDetails;

public interface IOrderService {
	public OrderDetails addOrder(OrderDetails order);
	public OrderDetails getOrder(long orderId);
	public OrderDetails deleteOrder(long orderId);
	public List<OrderDetails> getAllOrders();
}
