package in.dilshad.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(value = "member_details")
public class MemberDetails {

	@Id
	private Integer id;

	private String name;

	@Column("phone_number")
	private Long phoneNo;

	private String address;

	@Column("email_id")
	private String emailId;

	private String role;

	private String password;

	@Column("registered_date")
	private LocalDate registeredDate = LocalDate.now();

	@Column("last_login_date")
	private LocalDate lastLoginDate;

	public MemberDetails(String name, Long phoneNo, String address, String emailId, String role, String password) {
		super();
		this.name = name;
		this.phoneNo = phoneNo;
		this.address = address;
		this.emailId = emailId;
		this.role = role;
		this.password = password;
	}

}
