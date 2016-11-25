package cz.muni.fi.pa165.exceptions;

/**
 * Exception thrown by service layer.
 *
 * @author Jakub Fiser
 * @date 23/11/2016
 */
public class RegisterServiceException extends RuntimeException {
    public RegisterServiceException() {
        super();
    }

    public RegisterServiceException(String message) {
        super(message);
    }

    public RegisterServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterServiceException(Throwable cause) {
        super(cause);
    }

    public RegisterServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
