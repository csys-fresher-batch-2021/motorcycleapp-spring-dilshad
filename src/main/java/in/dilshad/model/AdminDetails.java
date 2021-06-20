package in.dilshad.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table(value = "motorcycleapp_admin_details")
public class AdminDetails {

	@Id
	private Integer id;

	@Column("admin_name")
	private String name;

	@Column("admin_id")
	private String adminId;

	@Column("admin_password")
	private String password;

}
