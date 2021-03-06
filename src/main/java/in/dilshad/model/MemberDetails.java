package in.dilshad.model;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import in.dilshad.constants.MemberRoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(value = "member_details")
public class MemberDetails {

	@Id
	private Integer id;

	private String name;

	@Column("phone_number")
	private Long phoneNo;

	private String address;

	@Column("email_id")
	private String email;

	private MemberRoleEnum role;

	private String password;

	@NotEmpty

	@Column("registered_date")
	private LocalDate registeredDate = LocalDate.now();

	@Column("last_login_date")
	private LocalDate lastLoginDate;

	// During Registration
	public MemberDetails(String name, Long phoneNo, String address, String emailId, MemberRoleEnum role, String password) {
		super();
		this.name = name;
		this.phoneNo = phoneNo;
		this.address = address;
		this.email = emailId;
		this.role = role;
		this.password = password;
	}

	// During login
	public MemberDetails(String emailId, MemberRoleEnum role, String password) {
		super();
		this.email = emailId;
		this.role = role;
		this.password = password;
	}

}
