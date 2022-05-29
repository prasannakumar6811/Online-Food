package onlinefood.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "admin_table")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Size(min = 3, max = 40, message = "Name should be length of 3-40")
	private String name;

	@Column(name = "emailId", nullable = false, unique = true)
	@NotEmpty
	@Email
	private String emailId;

	@NotEmpty
	@Pattern(regexp = "(^[0-9]{1,11})", message = "you can enter only digits")
	@Size(min = 10, max = 10, message = "Length of mobile number should be 10")
	private String mobileNumber;

	@NotEmpty
	@Size(min = 8, max = 12, message = "Password length should be minimum 8-12")
	private String password;

	public Admin() {
		super();
	}

	public Admin(long id, String name, String emailId, String mobileNumber, String password) {
		super();
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
