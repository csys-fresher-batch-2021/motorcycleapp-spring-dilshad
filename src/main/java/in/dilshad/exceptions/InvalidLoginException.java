package in.dilshad.exceptions;

@SuppressWarnings("serial")
public class InvalidLoginException extends RuntimeException{

	public InvalidLoginException(String message) {
		super(message);
	}
}
