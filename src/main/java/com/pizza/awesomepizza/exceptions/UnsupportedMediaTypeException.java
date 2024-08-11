package com.pizza.awesomepizza.exceptions;

import org.springframework.http.HttpStatus;

public class UnsupportedMediaTypeException extends HttpStatusException {

    public UnsupportedMediaTypeException() {
        super(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    public UnsupportedMediaTypeException(String message) {
        super(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message);
    }

    public UnsupportedMediaTypeException(String message, Throwable cause) {
        super(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message, cause);
    }

    public UnsupportedMediaTypeException(Throwable cause) {
        super(HttpStatus.UNSUPPORTED_MEDIA_TYPE, cause);
    }

    public UnsupportedMediaTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message, cause, enableSuppression, writableStackTrace);
    }

}
