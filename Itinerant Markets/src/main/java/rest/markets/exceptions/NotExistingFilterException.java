package rest.markets.exceptions;

public class NotExistingFilterException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	

	public NotExistingFilterException() {
		super();
	}

	public NotExistingFilterException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotExistingFilterException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotExistingFilterException(String message) {
		super(message);
	}

	public NotExistingFilterException(Throwable cause) {
		super(cause);
	}
	

}
