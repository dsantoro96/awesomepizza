package com.pizza.awesomepizza.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends HttpStatusException {

    public ConflictException() {
        super(HttpStatus.CONFLICT);
    }

    public ConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

    public ConflictException(String message, Throwable cause) {
        super(HttpStatus.CONFLICT, message, cause);
    }

    public ConflictException(Throwable cause) {
        super(HttpStatus.CONFLICT, cause);
    }

    public ConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(HttpStatus.CONFLICT, message, cause, enableSuppression, writableStackTrace);
    }

}
