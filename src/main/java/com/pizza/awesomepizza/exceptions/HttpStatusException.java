package com.pizza.awesomepizza.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class HttpStatusException extends RuntimeException {

    private HttpStatus status;

    public HttpStatusException(HttpStatus status) {
        this.status = status;
    }

    public HttpStatusException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatusException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatusException(HttpStatus status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    public HttpStatusException(HttpStatus status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }

}
