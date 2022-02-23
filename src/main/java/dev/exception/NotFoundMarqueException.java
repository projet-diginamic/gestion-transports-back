package dev.exception;

public class NotFoundMarqueException extends Exception {
    public NotFoundMarqueException() {
    }

    public NotFoundMarqueException(String message) {
        super(message);
    }

    public NotFoundMarqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundMarqueException(Throwable cause) {
        super(cause);
    }
}
