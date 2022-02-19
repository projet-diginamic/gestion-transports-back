package dev.exception;

public class CovoiturageCompletException extends RuntimeException {
    public CovoiturageCompletException() {
    }

    public CovoiturageCompletException(String message) {
        super(message);
    }

    public CovoiturageCompletException(String message, Throwable cause) {
        super(message, cause);
    }

    public CovoiturageCompletException(Throwable cause) {
        super(cause);
    }

    public CovoiturageCompletException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
