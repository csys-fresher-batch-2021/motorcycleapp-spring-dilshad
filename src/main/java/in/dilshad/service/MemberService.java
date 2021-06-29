package in.dilshad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import in.dilshad.dao.MemberRepository;
import in.dilshad.exceptions.InvalidLoginException;
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
	public MemberDetails register(MemberDetails memberDetails) throws Exception {

		memberValidator.validateMemberDetails(memberDetails);

		// Exception from DAO layer will be handled by Controller
		return memberRepository.save(memberDetails);
	}

	/**
	 * When valid email & password is provided - it returns the corresponding member
	 * details, else exception is thrown.
	 *
	 * @param memberDTO
	 * @return
	 */
	public MemberDetails login(MemberDetails memberDetails) {
		MemberDetails details = memberRepository
				.findByEmailAndPassword(memberDetails.getEmail(), memberDetails.getPassword()).orElse(null);
		if (details == null)
			throw new InvalidLoginException("Invalid email/password");
		return details;
	}
}
