package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.modules.base.OperationOutput;
import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public abstract class BaseController {
    public ResponseEntity<?> handleResponse(Either<ErrorWrapper, ? extends OperationOutput> result) {
        if (result.isRight()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getLeft());

    }
}
