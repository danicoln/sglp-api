package com.sglp.sglp_api.domain.exception;

public class ExameExistenteException extends NegocioException {

    public ExameExistenteException(String mensagem) {
        super(mensagem);
    }

    public ExameExistenteException(String mensagem, String id) {
        this(String.format("Já existe um exame para o laudo com o código %s", id));
    }
}
