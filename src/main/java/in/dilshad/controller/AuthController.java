package in.dilshad.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dilshad.dto.Message;
import in.dilshad.dto.RegisterDTO;
import in.dilshad.model.MemberDetails;
import in.dilshad.service.MemberService;

/**
 * Authorization - for member registration, login and logout functions.
 *
 * @author dils2654
 *
 */
@RestController
@RequestMapping("motorcycleapp/v1/auth/member")
public class AuthController {

	@Autowired
	MemberService memberService;

	/**
	 * Accepts member details of ADMIN & SELLER and passes it to service layer.
	 *
	 * url: http://localhost:9000/motorcycleapp/v1/auth/member/registration
	 *
	 * @param registerdto
	 * @return
	 */
	@PostMapping("registration")
	public ResponseEntity<?> memberRegistration(@Valid @RequestBody RegisterDTO registerdto) {

		// DTO to Model conversion
		MemberDetails memberDetails = new MemberDetails(registerdto.getName(), registerdto.getPhoneNo(),
				registerdto.getAddress(), registerdto.getEmailId(), registerdto.getRole(), registerdto.getPassword());

		try {
			MemberDetails register = memberService.register(memberDetails);
			return new ResponseEntity<>(register, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			Message message = new Message();
			message.setErrorMessage(e.getCause().getClass().toGenericString());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

	}
}
