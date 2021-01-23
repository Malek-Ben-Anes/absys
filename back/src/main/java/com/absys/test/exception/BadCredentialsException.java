package com.absys.test.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad request exception")
public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException(String message) {
        super(message);
        log.error("Error happened while executing controller.", this);
    }

    public BadCredentialsException(String message, Throwable cause) {
        super(message, cause);
        log.error("Error happened while executing controller.", this);
    }
}