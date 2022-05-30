package onlinefood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import onlinefood.dao.AdminDao;
import onlinefood.dao.FoodDao;
import onlinefood.dao.OrderDao;
import onlinefood.dao.UserDao;
import onlinefood.model.Admin;
import onlinefood.model.Food;
import onlinefood.model.OrderDetails;
import onlinefood.model.User;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class OnlineFoodOrderingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private AdminDao admindao;

	@Test
	@Order(1)
	public void testAdminSignup() {
		Admin ad1 = new Admin();
		ad1.setId(12);
		ad1.setName("Prasanna");
		ad1.setMobileNumber("1234567898");
		ad1.setEmailId("Prasanna@123");
		ad1.setPassword("Prasanna@12");
		admindao.save(ad1);
		assertNotNull(admindao.findByEmailId("Prasanna@123").getId());
	}

	@Test
	@Order(2)
	public void testAdminLogin() {
		Admin a = admindao.findByEmailId("Prasanna@123");
		admindao.save(a);
	}

	@Test
	@Order(3)
	public void testAdminDetailsByEmailId() {
		admindao.findByEmailId("Prasanna@123");
	}

	@Test
	@Order(4)
	public void testAdminUpdate() {
		Admin a = admindao.findByEmailId("Prasanna@123");
		a.setName("Mahesh");
		a.setMobileNumber("5896478523");
		a.setPassword("Mahesh@16");
		admindao.save(a);

	}

	@Test
	@Order(5)
	public void testAdminDeleteByEmailId() {
		Admin a = admindao.findByEmailId("Prasanna@123");
		admindao.delete(a);
	}

	@Autowired
	private UserDao userdao;

	@Test
	@Order(6)
	public void testAddUser() {
		User u = new User();

		u.setName("aisha");
		u.setPhoneNumber("7856984785");
		u.setAddress("kolhapur");
		u.setEmailId("aisha@gmail.com");
		u.setPassword("aisha@16");
		userdao.save(u);
	}

	@Test
	@Order(7)
	public void testLoginUser() {
		User u = userdao.findByEmailId("aisha@gmail.com");
		userdao.save(u);
	}

	@Test
	@Order(8)
	public void getUserById() {
		userdao.findByEmailId("aisha@gmail.com");
	}

	@Test
	@Order(9)
	public void testUpdateUser() {
		User u = userdao.findByEmailId("aisha@gmail.com");
		u.setName("aisha1");
		u.setPhoneNumber("7856923145");
		u.setAddress("pune");
		u.setPassword("aisha@16");
		userdao.save(u);
	}

	@Test
	@Order(10)
	public void testDeleteUser() {
		User u = userdao.findByEmailId("aisha@gmail.com");
		userdao.delete(u);
	}

	@Autowired
	private FoodDao fooddao;

	@Test
	@Order(11)
	public void testAddFood() {
		Food f1 = new Food();
		f1.setFoodName("poha");
		f1.setFoodPrice(50.0);
		f1.setFoodType("VegItem");
		fooddao.save(f1);
	}

	@Test
	@Order(12)
	public void testUpdateFood() {
		Food f = fooddao.findByFoodName("Lemon Rice");
		f.setFoodPrice(30);
		fooddao.save(f);
		assertEquals(30, f.getFoodPrice());
	}

	@Test
	@Order(13)
	public void testPrintFood() {
		fooddao.findByFoodName("roti");
	}

	@Test
	@Order(14)
	public void testDeleteFood() {
		Food f = fooddao.findByFoodName("poha");
		fooddao.delete(f);

	}

	@Test
	@Order(15)
	public void testByPrice() {
		fooddao.findByFoodPrice(50.0);
	}

	@Test
	@Order(16)
	public void testByType() {
		fooddao.findByFoodType("VegItem");
	}

	@Autowired
	private OrderDao orderdao;

	@Test
	@Order(17)
	public void testAddOrder() {
		OrderDetails od = new OrderDetails();
		od.setOrderId(1);
	}

	@Test
	@Order(18)
	public void testGetOrder() {
		OrderDetails o = orderdao.findByOrderId(6);
		assertEquals(6, o.getOrderId());
	}

	@Test
	@Order(19)
	public void testDeleteOrder() {
		OrderDetails o = orderdao.findByOrderId(5);
		orderdao.delete(o);
		assertThat(orderdao.existsById((long) 5)).isFalse();
	}
}
