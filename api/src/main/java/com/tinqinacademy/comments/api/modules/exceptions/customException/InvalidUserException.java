package com.tinqinacademy.comments.api.modules.exceptions.customException;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(message);
    }
}
