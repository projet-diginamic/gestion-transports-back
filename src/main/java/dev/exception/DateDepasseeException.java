package dev.exception;

public class DateDepasseeException extends RuntimeException{

    public DateDepasseeException() {
    }

    public DateDepasseeException(String message) {
        super(message);
    }

    public DateDepasseeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateDepasseeException(Throwable cause) {
        super(cause);
    }

    public DateDepasseeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
