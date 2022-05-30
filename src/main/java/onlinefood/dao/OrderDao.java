package onlinefood.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import onlinefood.model.OrderDetails;

public interface OrderDao extends JpaRepository<OrderDetails, Long> {
	public OrderDetails findByOrderId(long orderId);
	public List<OrderDetails> findFoodByOrderId(long orderId);
	public void save(List<OrderDetails> order);
}
