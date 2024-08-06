package com.tinqinacademy.comments.api.modules.exceptions.errorHandler;

import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;

public interface ErrorHandler {

    ErrorWrapper handleError(Throwable t);
}
