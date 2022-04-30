package com.webfinances.exceptions;

public class UniqueConstraintException extends RuntimeException {
    public UniqueConstraintException(String message) {
        super(message);
    }
}
