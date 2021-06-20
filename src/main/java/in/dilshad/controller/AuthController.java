package in.dilshad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dilshad.dto.AdminDetailsDTO;
import in.dilshad.dto.Message;
import in.dilshad.exception.DBException;
import in.dilshad.exception.ValidationException;
import in.dilshad.model.AdminDetails;
import in.dilshad.service.AdminService;

@RestController
@RequestMapping("motorcycleapp/v1/auth/admin")
public class AuthController {

	@Autowired
	AdminService adminService;

	// http://localhost:9000/motorcycleapp/v1/auth/admin/registration
	@PostMapping("registration")
	public ResponseEntity<?> adminRegister(@RequestBody AdminDetailsDTO admindto) {

		System.out.println("Inside motorcycleapp/v1/auth/admin/registration");
		AdminDetails adminDetails = new AdminDetails();
		adminDetails.setName(admindto.getName());
		adminDetails.setAdminId(admindto.getAdminId());
		adminDetails.setPassword(admindto.getPassword());

		Message message = new Message();

		try {
			adminService.register(adminDetails);
			message.setInfoMessage("Registration Successfull");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (ValidationException e) {
			message.setErrorMessage("Enter details in correct format");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} catch (DBException e) {
			message.setErrorMessage("Unable to connect to Database / Record Already Exist");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			message.setErrorMessage("Unexpected Error. Try again later");
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}
	}

}
