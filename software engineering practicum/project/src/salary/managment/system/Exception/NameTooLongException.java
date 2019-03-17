/**
 * 
 */
package salary.managment.system.Exception;

/**
 * @author Tortoise
 *
 */
public class NameTooLongException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5361020121523904310L;

	/**
	 * 
	 */
	public NameTooLongException() {
		super("name length too long!");
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public NameTooLongException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public NameTooLongException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NameTooLongException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NameTooLongException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
