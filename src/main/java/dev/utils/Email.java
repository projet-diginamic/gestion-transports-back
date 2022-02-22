package dev.utils;

import dev.exception.entites.reservation.Reservation;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Email {

    private JavaMailSender sender;

    public Email(JavaMailSender sender) {
        this.sender = sender;
    }

    public void envoyerEmail(String raison, Reservation resa){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(resa.getPassager().getEmail());
        message.setSubject("Annulation de votre réservation");
        message.setText("Votre réservation :\n" + resa.toString() + "a été annulée. Motif : "+raison);
        this.sender.send(message);
    }
}
