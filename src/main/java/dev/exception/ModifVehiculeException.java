package dev.exception;

public class ModifVehiculeException extends Exception {

    public ModifVehiculeException() {
    }

    public ModifVehiculeException(String message) {
        super(message);
    }

    public ModifVehiculeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModifVehiculeException(Throwable cause) {
        super(cause);
    }
}
