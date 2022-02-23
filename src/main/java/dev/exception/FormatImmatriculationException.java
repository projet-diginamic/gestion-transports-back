package dev.exception;

public class FormatImmatriculationException extends Exception {

    public FormatImmatriculationException() {
    }

    public FormatImmatriculationException(String message) {
        super(message);
    }

    public FormatImmatriculationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatImmatriculationException(Throwable cause) {
        super(cause);
    }
}
