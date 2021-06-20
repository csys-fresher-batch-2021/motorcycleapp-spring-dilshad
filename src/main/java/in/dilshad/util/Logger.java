package in.dilshad.util;

public class Logger {
	// To avoid sonar cloud error
	private Logger() {
		// to avoid object creation
	}

	/**
	 * This method will print whatever the parameter is passed
	 * 
	 * @param message
	 */
	public static void println(String message) { // Replace String with Object
		System.out.println(message);
	}

	public static void println(Object printStackTrace) {
		System.out.println(printStackTrace);
		
	}

}
