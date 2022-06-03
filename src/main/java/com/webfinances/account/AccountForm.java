package com.webfinances.account;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AccountForm {
    @NotBlank(message = "Please enter a name for your account!")
    @Size(min = 2, max = 32, message = "The account name has to be between 2 and 32 characters long!")
    private String name;

    @NotBlank(message = "Please enter a starting balance!")
    @Pattern(regexp = "^[0-9-]*\\.[0-9]{2}$", message = "The starting balance has to be in the format 123.45")
    private String balance;
}
