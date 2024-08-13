package com.sglp.sglp_api.domain.exception;

public class PeritoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public PeritoNaoEncontradoException(String msg) {
        super(msg);
    }

    public PeritoNaoEncontradoException(String id, String msg) {
        super(msg);
    }

}
