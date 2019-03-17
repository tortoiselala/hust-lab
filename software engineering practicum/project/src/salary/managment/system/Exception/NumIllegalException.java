/**
 * 
 */
package salary.managment.system.Exception;

/**
 * @author Tortoise
 *
 */
public class NumIllegalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7458731529913121399L;

	/**
	 * 
	 */
	public NumIllegalException() {
		super("Illegal num!");
	}

	/**
	 * @param message
	 */
	public NumIllegalException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public NumIllegalException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NumIllegalException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NumIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
