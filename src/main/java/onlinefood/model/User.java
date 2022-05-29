package onlinefood.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@Size(min = 2, max = 30, message = "name should be length of 2-30.")
	private String name;

	@NotEmpty
	@Pattern(regexp = "(^[0-9]{1,11})", message = "you can enter only digits")
	@Size(min = 10, max = 10, message = "Length of Phone Number Should be 10.")
	private String phoneNumber;

	@NotEmpty
	@Size(min = 2, max = 40, message = "Length of Address should be 2-40. ")
	private String address;

	@Column(name = "emailId", nullable = false, unique = true)
	@NotEmpty
	@Email
	private String emailId;

	@NotEmpty
	@Size(min = 8, message = "password Not valid and minimum length should be 8.")
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<OrderDetails> order = new ArrayList<OrderDetails>();

	public List<OrderDetails> getOrder() {
		return order;
	}

	public void setOrder(List<OrderDetails> order) {
		this.order = order;
	}

	public User() {
		super();
	}

	public User(int id, String name, String phoneNumber, String address, String emailId, String password) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.emailId = emailId;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ ", emailId=" + emailId + ", password=" + password + ", order=" + order + "]";
	}
}
