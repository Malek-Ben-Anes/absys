package com.absys.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "resource is duplicated")
public class ResourceDuplicatedException extends RuntimeException {
    public ResourceDuplicatedException(String message) {
        super(message);
    }

    public ResourceDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }
}