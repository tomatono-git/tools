/**
 *
 */
package tools.daogen.exception;

/**
 *
 *
 */
public class DaogenException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	public DaogenException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DaogenException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DaogenException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DaogenException(Throwable cause) {
		super(cause);
	}
}
