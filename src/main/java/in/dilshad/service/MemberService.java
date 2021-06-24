package in.dilshad.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.dilshad.dao.MemberRepository;
import in.dilshad.model.MemberDetails;
import in.dilshad.validation.MemberValidator;

@Service
public class MemberService {

	@Autowired
	MemberValidator memberValidator;

	@Autowired
	MemberRepository memberRepository;

	public MemberDetails register(@Valid MemberDetails memberDetails) {

		memberValidator.validateMemberDetails(memberDetails);
		return memberRepository.save(memberDetails);
	}
}
