package com.sglp.sglp_api.domain.exception;

public class NomeacaoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public NomeacaoNaoEncontradaException(String mensagem) {
        super(mensagem);

    }

    public NomeacaoNaoEncontradaException(String mensagem, String nomeacaoId) {
        this(String.format("Não existe uma nomeação com o código %d", nomeacaoId));
    }
}
