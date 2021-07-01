package in.dilshad.exceptions;

/**
 * ValidationException - user Defined
 *
 * @author dils2654
 *
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * User Message
	 *
	 * @param message
	 */
	public ValidationException(String message) {
		super(message);
	}

	/**
	 * User message & Exception e
	 *
	 * @param e
	 * @param message
	 */
	public ValidationException(Throwable e, String message) {
		super(message, e);
	}
}
