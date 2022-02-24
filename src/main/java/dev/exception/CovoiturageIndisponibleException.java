package dev.exception;

public class CovoiturageIndisponibleException extends RuntimeException{
    public CovoiturageIndisponibleException() {
    }

    public CovoiturageIndisponibleException(String message) {
        super(message);
    }

    public CovoiturageIndisponibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public CovoiturageIndisponibleException(Throwable cause) {
        super(cause);
    }
}
