package com.sglp.sglp_api.api.exceptions;

import com.sglp.sglp_api.domain.exception.ExameExistenteException;
import com.sglp.sglp_api.domain.exception.NomeacaoExistenteException;
import lombok.Getter;
import lombok.Setter;
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

    @ExceptionHandler(NomeacaoExistenteException.class)
    public ResponseEntity<?> handleNomeacaoExistenteException(NomeacaoExistenteException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(errorMessage), HttpStatus.CONFLICT);
    }

    @Getter
    @Setter
    private static class ErrorResponse {
        private String mensagem;

        public ErrorResponse(String mensagem) {
            this.mensagem = mensagem;
        }
    }
}
