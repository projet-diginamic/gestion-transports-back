package dev.exception;

public class NotFoundVehiculeDetailException extends Exception {

	public NotFoundVehiculeDetailException() {
	}

	public NotFoundVehiculeDetailException(String message) {
		super(message);
	}

	public NotFoundVehiculeDetailException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundVehiculeDetailException(Throwable cause) {
		super(cause);
	}
}
