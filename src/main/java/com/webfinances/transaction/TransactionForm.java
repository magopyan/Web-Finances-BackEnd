package com.webfinances.transaction;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class TransactionForm {

    @NotBlank(message = "Please enter a valid amount! (Example: 123.45)")
    @Pattern(regexp = "^[0-9]*\\.?[0-9]{0,2}$", message = "Please enter a valid amount! (Example: 123.45)")
    private String amount;

    @NotBlank
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Please select a valid date!")
    private String date;

    @Size(min = 0, max = 64, message = "The note can be up to 64 characters long!")
    private String note;
}
