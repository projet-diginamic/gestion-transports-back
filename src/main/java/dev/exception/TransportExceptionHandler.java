package dev.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransportExceptionHandler {

    @ExceptionHandler(value = {CovoiturageCompletException.class})
    public ResponseEntity<String> onCovoiturageCompletException(CovoiturageCompletException e){
        return ResponseEntity.badRequest().header("info", e.getMessage()).build();
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<String> onNotFoundException(NotFoundException e){
        return ResponseEntity.notFound().header("info","entité introuvable").build();
    }

}
