package in.dilshad.exception;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -8456094852296448726L;

	/**
	 * User Defined Exception - for validator
	 * 
	 * @param message
	 */
	public ValidationException(String message) {
		super(message);
	}

}
