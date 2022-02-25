package dev.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransportExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String onException(Exception e) {
		return e.getMessage();
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class, ConstraintViolationException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String onDataIntegrityViolationException(ConstraintViolationException e) {
		return e.getSQLException().getMessage();
	}

	@ExceptionHandler(value = { MailAuthenticationException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String onMailAuthenticationException(MailAuthenticationException e) {
		return "Erreur : Email d'annulation non envoyé";
	}

	@ExceptionHandler(value = { CovoiturageIndisponibleException.class, CovoiturageCompletException.class })
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public String onCovoiturageCompletException(Exception e) {
		return e.getMessage();
	}

	@ExceptionHandler(value = { NotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String onNotFoundException(NotFoundException e) {
		return "entité introuvable";// Voir les messages quand il y aura le temps
	}

	@ExceptionHandler(value = { DateDepasseeException.class })
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public String onDateDepasseeException(DateDepasseeException e) {
		return "Date de départ dépassée";
	}

	@ExceptionHandler(value = { NotFoundImmatriculationException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String onNotFoundImmatriculation(NotFoundImmatriculationException e) {
		return e.getMessage();
	}

	@ExceptionHandler(value = { NotFoundVehiculeDetailException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String onNotFoundVehiculeDetailException(NotFoundVehiculeDetailException e) {
		return e.getMessage();
	}

	@ExceptionHandler(value = { NotFoundMarqueException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String onNotFoundMarque(NotFoundMarqueException e) {
		return e.getMessage();
	}

	@ExceptionHandler(value = { ListeVideException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String onListeVide(ListeVideException e) {
		return e.getMessage();
	}

	@ExceptionHandler(value = { FormatImmatriculationException.class })
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public String onFormatImmatriculation(FormatImmatriculationException e) {
		return e.getMessage();
	}

}
