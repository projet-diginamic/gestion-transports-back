package dev.exception;

public class NotFoundImmatriculationException extends Exception {


    public NotFoundImmatriculationException() {
    }

    public NotFoundImmatriculationException(String message) {
        super(message);
    }

    public NotFoundImmatriculationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundImmatriculationException(Throwable cause) {
        super(cause);
    }
}
