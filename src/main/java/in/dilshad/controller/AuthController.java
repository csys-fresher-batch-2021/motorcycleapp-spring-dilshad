package in.dilshad.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.dilshad.dto.RegisterDTO;
import in.dilshad.model.MemberDetails;
import in.dilshad.service.MemberService;

@RestController
@RequestMapping("motorcycleapp/v1/auth/member")
public class AuthController {

	@Autowired
	MemberService memberService;

	// http://localhost:9000/motorcycleapp/v1/auth/member/registration
	@PostMapping("registration")
	public MemberDetails memberRegistration(@Valid @RequestBody RegisterDTO registerdto) {

		// DTO to Model conversion
		MemberDetails memberDetails = new MemberDetails(registerdto.getName(), registerdto.getPhoneNo(),
				registerdto.getAddress(), registerdto.getEmailId(), registerdto.getRole(), registerdto.getPassword());

		return memberService.register(memberDetails);

	}
}
