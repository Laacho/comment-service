package com.tinqinacademy.comments.api.modules.exceptions.errorHandler;

import com.tinqinacademy.comments.api.modules.base.OperationInput;
import com.tinqinacademy.comments.api.modules.exceptions.baseError.Error;
import com.tinqinacademy.comments.api.modules.exceptions.customException.InvalidCommentIdException;
import com.tinqinacademy.comments.api.modules.exceptions.customException.InvalidUserException;
import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ErrorHandlerImpl implements ErrorHandler {
    @Override
    public ErrorWrapper handleError(Throwable t) {

        if (t instanceof InvalidUserException) {
            ErrorWrapper errorWrapper = ErrorWrapper.builder().build();
            errorWrapper.addErrors(
                    Error.builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(t.getMessage())
                            .build());
            return errorWrapper;
        }
        if(t instanceof InvalidCommentIdException){
            ErrorWrapper errorWrapper = ErrorWrapper.builder().build();
            errorWrapper.addErrors(
                    Error.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message(t.getMessage())
                            .build());
            return errorWrapper;
        }
        return null;

    }

    @Override
    public ErrorWrapper handleViolations(Set<ConstraintViolation<OperationInput>> violations, HttpStatusCode statusCode) {
        List<Error> responses = violations.stream()
                .map(v -> Error.builder()
                        .message(v.getMessage())
                        .build())
                .toList();

        return ErrorWrapper.builder()
                .errors(responses)
                .build();
    }
}
