package dev.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransportExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> onException(Exception e){
        return ResponseEntity.badRequest().header("info", "Erreur inattendue - requête rejetée").build();
    }

    @ExceptionHandler(value =  {MailAuthenticationException.class})
    public ResponseEntity<String> onMailAuthenticationException(MailAuthenticationException e){
        return ResponseEntity.internalServerError().header("info","Erreur : Email d'annulation non envoyé").build();
    }

    @ExceptionHandler(value = {CovoiturageCompletException.class})
    public ResponseEntity<String> onCovoiturageCompletException(CovoiturageCompletException e){
        return ResponseEntity.badRequest().header("info", e.getMessage()).build();
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<String> onNotFoundException(NotFoundException e){
        return ResponseEntity.notFound().header("info","entité introuvable").build();
    }

    @ExceptionHandler(value = {DateDepasseeException.class})
    public ResponseEntity<String> onDateDepasseeException(DateDepasseeException e){
        return ResponseEntity.badRequest().header("info", "Date de départ dépassée").build();
    }

    @ExceptionHandler(value = {NotFoundImmatriculationException.class})
    public ResponseEntity<String> onNotFoundImmatriculation(NotFoundImmatriculationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = {NotFoundMarqueException.class})
    public ResponseEntity<String> onNotFoundMarque(NotFoundMarqueException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = {ListeVideException.class})
    public ResponseEntity<String> onListeVide(ListeVideException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
