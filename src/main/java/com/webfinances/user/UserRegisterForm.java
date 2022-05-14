package com.webfinances.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegisterForm {

    @NotBlank(message = "Please enter a valid email!")
    @Email(message = "Please enter a valid email!")
    private String email;

    @NotBlank(message = "Please enter a password!")
    @Size(min = 8, max = 32, message = "Your password has to be between 8 and 32 characters long!")
    private String password;

    @NotBlank(message = "Please repeat your password!")
    @Size(min = 8, max = 32, message = "Your password has to be between 8 and 32 characters long!")
    private String repeatPassword;
}


