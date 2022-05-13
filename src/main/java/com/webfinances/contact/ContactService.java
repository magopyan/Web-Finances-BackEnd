package com.webfinances.contact;

import com.webfinances.config.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class ContactService {

    private EmailSenderService emailSenderService;

    @Autowired
    public ContactService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public Map<String, String> sendEmailAndReturnMessage(ContactForm contactForm) {
        emailSenderService.sendEmail(contactForm.getName(), contactForm.getEmail(), contactForm.getMessage());
        return Collections.singletonMap("response", "Message successfully submitted. ✔️");
    }
}
