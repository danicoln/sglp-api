package com.sglp.sglp_api.api.exceptions;

import com.sglp.sglp_api.domain.exception.ExameExistenteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExameExistenteException.class)
    public ResponseEntity<Object> handleExameExistenteException(ExameExistenteException ex,
                                                                WebRequest request) {
        String response = ex.getMessage();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
}
