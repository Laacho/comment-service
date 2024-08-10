package com.tinqinacademy.comments.core.services.implementations;

import com.tinqinacademy.comments.api.modules.base.OperationInput;
import com.tinqinacademy.comments.api.modules.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import io.vavr.control.Either;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BaseOperationProcessor {
    private final Validator validator;
    protected final ErrorHandler errorHandler;


    public BaseOperationProcessor(Validator validator, ErrorHandler errorHandler) {
        this.validator = validator;
        this.errorHandler = errorHandler;
    }

    public Either<ErrorWrapper, ? extends OperationInput> validateInput(OperationInput input) {
        Set<ConstraintViolation<OperationInput>> constraintViolations = validator.validate(input);

        if (constraintViolations.isEmpty()) {
            return Either.right(input);
        }

        ErrorWrapper errors = errorHandler.handleViolations(constraintViolations, HttpStatus.BAD_REQUEST);
        return Either.left(errors);
    }
}
