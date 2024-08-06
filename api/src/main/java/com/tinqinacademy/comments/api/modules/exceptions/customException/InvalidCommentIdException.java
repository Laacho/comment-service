package com.tinqinacademy.comments.api.modules.exceptions.customException;

public class InvalidCommentIdException extends RuntimeException {
    public InvalidCommentIdException(String message) {
        super(message);
    }
}
