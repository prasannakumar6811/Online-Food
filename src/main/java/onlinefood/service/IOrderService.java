package onlinefood.service;

import onlinefood.model.OrderDetails;

public interface IOrderService {
	public OrderDetails addOrder(OrderDetails order);
	public OrderDetails getOrder(long orderId);
	public OrderDetails deleteOrder(long orderId);
}
