package in.dilshad.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.executable.ValidateOnExecution;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import in.dilshad.exception.ValidationException;
import in.dilshad.model.AdminDetails;
import in.dilshad.util.StringValidator;
import lombok.NoArgsConstructor;

@Component
public class AdminValidator {

	/**
	 * Check admin name,admin Id & Password and throws exception if invalid.
	 * 
	 * @param admin
	 * @param password
	 * @throws ValidationException
	 */
	public void validateRegister(AdminDetails admin) throws ValidationException {
		String name = admin.getName();
		String id = admin.getAdminId();
		String password = admin.getPassword();

		List<String> errorList = new ArrayList<>();
		if (!isValidAdmin(id))
			errorList.add("Enter id in proper format");
		if (!isStrongPassword(password))
			errorList.add("Enter minimum 7 characters");
		if (!isValidName(name))
			errorList.add("Enter name in proper format");

		if (!errorList.isEmpty()) {
			String invalidFields = String.join(" | " + errorList);
			throw new ValidationException(invalidFields);
		}
	}

	/**
	 * Checks if admin name consist of only alphabets and the lenght ranges from 2
	 * to 20 characters.
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isValidName(String name) {
		return StringValidator.isAlpha(name) && StringValidator.isValidString(name, 20, 2);
	}

	/**
	 * Validates Admin id. Length must be in the range 2 to 15 characters. Special
	 * characters should not be present.
	 * 
	 * @param admin
	 * @return
	 */
	public static boolean isValidAdmin(String Id) {
		boolean isValid = false;
		if (StringValidator.isValidString(Id, 15, 2) && !StringValidator.isSpecialCharPresent(Id))
			isValid = true;
		return isValid;
	}

	/**
	 * Checks if the password length is between 5 and 20 characters.
	 * 
	 * @param password
	 * @return
	 */
	public static boolean isStrongPassword(String password) {
		return StringValidator.isValidString(password, 20, 7);
	}

}
