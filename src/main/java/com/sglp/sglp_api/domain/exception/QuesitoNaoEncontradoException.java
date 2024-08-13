package com.sglp.sglp_api.domain.exception;

public class QuesitoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public QuesitoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public QuesitoNaoEncontradoException(String mensagem, String quesitoId) {
        this(String.format("Não existe quesito com o código %s", quesitoId));
    }
}
