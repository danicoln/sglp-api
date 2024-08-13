package com.sglp.sglp_api.domain.exception;

public class LaudoPericialNaoEncontradoException extends EntidadeNaoEncontradaException {

    public LaudoPericialNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public LaudoPericialNaoEncontradoException(String mensagem, String laudoId) {
        super(mensagem);
    }
}
