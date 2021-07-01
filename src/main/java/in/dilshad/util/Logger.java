package in.dilshad.util;

public class Logger {
	private Logger() {
		// to avoid object creation
	}

	/**
	 * This method will print whatever the String is passed.
	 *
	 * @param message
	 */
	public static void message(String message) {
		System.out.println(message);
	}

	/**
	 * This method will print whatever the Object is passed
	 *
	 * @param printStackTrace
	 */
	public static void log(Object obj) {
		System.out.println(obj);

	}

	/**
	 * To print the trace of the Exception message.
	 *
	 * @param e
	 */
	public static void trace(Exception e) {
		e.printStackTrace();
	}

}
