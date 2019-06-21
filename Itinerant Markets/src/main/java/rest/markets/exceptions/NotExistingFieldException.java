package rest.markets.exceptions;

/**
 * This class inherits from RuntimeExcepion and implements an exception that occurs 
 * when a wrong field name is sent in a user request.
 */
public class NotExistingFieldException extends RuntimeException {

	private static final long serialVersionUID = 2L;

	public NotExistingFieldException() {
		super();
	}

	public NotExistingFieldException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotExistingFieldException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotExistingFieldException(String message) {
		super(message);
	}

	public NotExistingFieldException(Throwable cause) {
		super(cause);
	}
	
}
