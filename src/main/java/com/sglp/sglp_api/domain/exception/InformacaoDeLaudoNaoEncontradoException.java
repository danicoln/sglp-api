package com.sglp.sglp_api.domain.exception;

public class InformacaoDeLaudoNaoEncontradoException extends RuntimeException {

    public InformacaoDeLaudoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public InformacaoDeLaudoNaoEncontradoException(String mensagem, String id) {
        this(String.format(mensagem, id));
    }
}
