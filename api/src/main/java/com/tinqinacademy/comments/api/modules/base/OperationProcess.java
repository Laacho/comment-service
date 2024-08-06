package com.tinqinacademy.comments.api.modules.base;


import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import io.vavr.control.Either;

public interface OperationProcess<I extends OperationInput, O extends OperationOutput>  {

    Either<ErrorWrapper,O> process(I input);
}
