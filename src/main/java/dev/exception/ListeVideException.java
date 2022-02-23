package dev.exception;

public class ListeVideException extends Exception {

    public ListeVideException() {
    }

    public ListeVideException(String message) {
        super(message);
    }

    public ListeVideException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListeVideException(Throwable cause) {
        super(cause);
    }
}
