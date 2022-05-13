package com.webfinances.contact;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ContactForm {

    @NotBlank(message = "Please enter your name!")
    @Size(min = 2, max = 64, message = "Your name has to be between 2 and 64 characters long!")
    private String name;

    @NotBlank(message = "Please enter a valid email!")
    @Email(message = "Please enter a valid email!")
    private String email;

    @NotBlank(message = "Please leave us a message!")
    @Size(min = 2, max = 64, message = "Your message has to be between 2 and 1024 characters long!")
    private String message;
}
