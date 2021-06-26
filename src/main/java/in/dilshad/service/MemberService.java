package in.dilshad.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.dilshad.dao.MemberRepository;
import in.dilshad.model.MemberDetails;
import in.dilshad.validation.MemberValidator;

/**
 * Manages the member details.
 *
 * @author dils2654
 *
 */
@Service
public class MemberService {

	@Autowired
	MemberValidator memberValidator;

	@Autowired
	MemberRepository memberRepository;

	/**
	 * Accepts member details and passes it to DAO after Validation.
	 *
	 * @param memberDetails
	 * @return
	 * @throws Exception
	 */
	public MemberDetails register(@Valid MemberDetails memberDetails) throws Exception {

		memberValidator.validateMemberDetails(memberDetails);

		// Exception from DAO layer will be handled by Controller
		return memberRepository.save(memberDetails);


	}
}
