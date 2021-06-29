package in.dilshad.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dilshad.dto.MemberDTO;
import in.dilshad.dto.Message;
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
	public ResponseEntity<?> memberRegistration(@Valid @RequestBody MemberDTO registerdto) {

		// DTO to Model conversion
		MemberDetails memberDetails = new MemberDetails(registerdto.getName(), registerdto.getPhoneNo(),
				registerdto.getAddress(), registerdto.getEmail(), registerdto.getRole(), registerdto.getPassword());

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

	/**
	 * Login feature: When valid email & password is provided - it returns the
	 * corresponding member details, else exception is thrown by the service layer
	 * which is handled.
	 *
	 * url: http://localhost:9000/motorcycleapp/v1/auth/member/login
	 *
	 * @param login
	 * @return
	 */
	@PostMapping("login")
	public ResponseEntity<?> login1(@RequestBody MemberDTO loginDto) {

		try {
			MemberDetails memberDetails = new MemberDetails(loginDto.getEmail(), loginDto.getRole(),
					loginDto.getPassword());
			MemberDetails details = memberService.login(memberDetails);
			return new ResponseEntity<>(details, HttpStatus.OK);
		} catch (Exception e) {
			Message message = new Message();
			message.setControllerMessage(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
		}

	}
}
