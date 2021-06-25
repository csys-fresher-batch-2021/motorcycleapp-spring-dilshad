package in.dilshad.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import in.dilshad.model.MemberRoleEnum;
import lombok.Data;

@Data
public class RegisterDTO {
	//Totally there are 6 fields

	@NotNull
	@NotEmpty(message = "Member name cannot be empty")
	@Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
	private String name;

	@NotNull
	private Long phoneNo; // Unique

	@NotNull
	@Size(min = 10, max = 100, message = "Address must have minimum 10 characters & maximum 100 characters")
	private String address;

	@NotNull
	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Email should be valid")
	private String emailId; // Unique

	private MemberRoleEnum role;

	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(min = 7, max = 30, message = "Password must be greater than 7 characters & must not be too long")
	private String password;

}
