package salary.managment.system.Exception;

public class LenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5185385891865872328L;

	public LenException() {
		super("Illegal length of input!");
	}

	public LenException(String message) {
		super(message);
	}

	public LenException(Throwable cause) {
		super(cause);
	}

	public LenException(String message, Throwable cause) {
		super(message, cause);
	}

	public LenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
