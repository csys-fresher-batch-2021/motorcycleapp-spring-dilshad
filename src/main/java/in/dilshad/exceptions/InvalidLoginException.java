package in.dilshad.exceptions;

/**
 * Invalid Login Exception - User defined.
 *
 * @author dils2654
 *
 */
@SuppressWarnings("serial")
public class InvalidLoginException extends RuntimeException{

	/**
	 * User Message
	 *
	 * @param message
	 */
	public InvalidLoginException(String message) {
		super(message);
	}

	/**
	 * User message & Exception e
	 *
	 * @param e
	 * @param message
	 */
	public InvalidLoginException(Throwable e, String message) {
		super(message, e);
	}
}
