package com.webfinances.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String name, String email, String message) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("mihael.b.agopyan@gmail.com");
        mail.setSubject(name);
        mail.setText("Sent by: " + email + "\n\n" + message);
        javaMailSender.send(mail);
    }
}
