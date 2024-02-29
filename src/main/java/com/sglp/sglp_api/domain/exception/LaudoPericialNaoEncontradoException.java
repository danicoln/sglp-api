package com.sglp.sglp_api.domain.exception;

public class LaudoPericialNaoEncontradoException extends EntidadeNaoEncontradaException {

    public LaudoPericialNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public LaudoPericialNaoEncontradoException(Long laudoId) {
        this(String.format("Não existe um Laudo Pericial com o códgo %d", laudoId));
    }
}
