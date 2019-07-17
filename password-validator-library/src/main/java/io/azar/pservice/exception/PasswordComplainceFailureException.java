package io.azar.pservice.exception;

/**
 * 
 * @author SK
 *
 */
public class PasswordComplainceFailureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6670043012334874923L;

	public PasswordComplainceFailureException() {
		super();
	}

	public PasswordComplainceFailureException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PasswordComplainceFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordComplainceFailureException(String message) {
		super(message);
	}

	public PasswordComplainceFailureException(Throwable cause) {
		super(cause);
	}

}
