package com.pizza.awesomepizza.components;

import com.pizza.awesomepizza.dto.ErrorResponseDTO;
import com.pizza.awesomepizza.exceptions.HttpStatusException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class HttpStatusExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({HttpStatusException.class})
    public ResponseEntity<Object> handleHttpStatusException(HttpStatusException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);

        ErrorResponseDTO response = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .status(ex.getStatus())
                .build();

        return handleExceptionInternal(ex, response, new HttpHeaders(), ex.getStatus(), request);
    }

}
