package com.sglp.sglp_api.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {

    public EntidadeNaoEncontradaException(String msg) {
        super(msg);
    }
}
