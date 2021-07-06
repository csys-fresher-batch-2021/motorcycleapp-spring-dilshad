package in.dilshad.exceptions;

public class DBException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -4496626400894163213L;

	public DBException(String message) {
		super(message);
	}

	public DBException(Throwable e, String message) {
		super(message, e);
	}
}
