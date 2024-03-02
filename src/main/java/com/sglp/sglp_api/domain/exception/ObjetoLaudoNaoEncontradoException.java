package com.sglp.sglp_api.domain.exception;

public class ObjetoLaudoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ObjetoLaudoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public ObjetoLaudoNaoEncontradoException(String mensagem, String objetoId) {
        this(String.format("Não existe um objeto com o código %d", objetoId));
    }
}
