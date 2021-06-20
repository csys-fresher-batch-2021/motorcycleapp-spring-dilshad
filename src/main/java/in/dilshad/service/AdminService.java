package in.dilshad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.dilshad.dao.AdminRepository;
import in.dilshad.exception.DBException;
import in.dilshad.exception.ValidationException;
import in.dilshad.model.AdminDetails;
import in.dilshad.validation.AdminValidator;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	AdminValidator adminValidator;

	public void register(AdminDetails adminDetails) throws ValidationException, DBException {
		System.out.println("Inside Admin Service");
		System.out.println(adminDetails);

		// Throws Validation Exception if entered fields are not in proper format
		adminValidator.validateRegister(adminDetails);

		try {
			adminRepository.save(adminDetails);
		} catch (Exception e) {
			throw new DBException("Database Exception");
		}
	}
}
