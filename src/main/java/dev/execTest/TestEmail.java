package dev.execTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class TestEmail implements CommandLineRunner {

    @Autowired
    JavaMailSender sender;

    @Override
    public void run(String... args) throws Exception {
        SimpleMailMessage mail  = new SimpleMailMessage();
        mail.setTo("leheurt.kevin@gmail.com");
        mail.setSubject("ça marche");
        mail.setText("c'est cool");
        System.out.println(mail.getFrom());
        sender.send(mail);
    }

}
