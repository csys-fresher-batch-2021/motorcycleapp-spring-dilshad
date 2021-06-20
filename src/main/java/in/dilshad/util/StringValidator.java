package in.dilshad.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {

	private StringValidator() {
		// Default constructor
	}

	/**
	 * Method overloading is deployed. Checks the presence of null character and
	 * whitespace.
	 * 
	 * @param inputString
	 * @return
	 */
	public static boolean isValidString(String inputString) {
		boolean isValid = false;
		if (inputString != null && !inputString.trim().isEmpty()) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * Checks the presence of null character and whitespace. Check if length of the
	 * string is less than or equal to maximum value.
	 * 
	 * @param inputString
	 * @param max
	 * @return
	 */
	public static boolean isValidString(String inputString, int max) {
		boolean isValid = false;
		if (isValidString(inputString) && inputString.length() <= max) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * Checks the presence of null character and whitespace. Check if length of the
	 * string is less than or equal to maximum value. Check if length of the string
	 * is greater than or equal to minimum value.
	 * 
	 * @param inputString
	 * @param max
	 * @param min
	 * @return
	 */
	public static boolean isValidString(String inputString, int max, int min) {
		boolean isValid = false;
		if (isValidString(inputString, max) && inputString.length() >= min) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * Checks for the presence of Special Character.
	 * 
	 * @param inputString
	 * @return
	 */
	public static boolean isSpecialCharPresent(String inputString) {
		if (isValidString(inputString)) {
			Pattern pattern = Pattern.compile("[^a-zA-Z0-9' ']");
			Matcher matcher = pattern.matcher(inputString.trim());
			return matcher.find();
		} else
			return false;
	}

	/**
	 * Checks the presence of numbers in the string
	 * 
	 * @param inputString
	 * @return
	 */
	public static boolean isNumberPresent(String inputString) {
		if (isValidString(inputString)) {
			Pattern pattern = Pattern.compile("[0-9]");
			Matcher matcher = pattern.matcher(inputString);
			return matcher.find();
		} else
			return false;
	}

	/**
	 * Checks the presence of Alphanumeric character only.
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isAlpha(String input) {
		return !isSpecialCharPresent(input) && !isNumberPresent(input) && isValidString(input);
	}

}
