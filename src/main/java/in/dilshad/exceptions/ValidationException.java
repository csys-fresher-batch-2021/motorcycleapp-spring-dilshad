package in.dilshad.exceptions;

/**
 * ValidationException - user Defined
 *
 * @author dils2654
 *
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidationException(String message) {
		super(message);
	}
}
