package com.tinqinacademy.comments.api.modules.exceptions.errorHandler;

import com.tinqinacademy.comments.api.modules.base.OperationInput;
import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatusCode;

import java.util.Set;

public interface ErrorHandler {

    ErrorWrapper handleError(Throwable t);
    ErrorWrapper handleViolations(Set<ConstraintViolation<OperationInput>> violations, HttpStatusCode statusCode) ;

}
