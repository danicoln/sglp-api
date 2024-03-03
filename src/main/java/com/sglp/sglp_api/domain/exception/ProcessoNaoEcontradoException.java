package com.sglp.sglp_api.domain.exception;

public class ProcessoNaoEcontradoException extends EntidadeNaoEncontradaException {

    public ProcessoNaoEcontradoException(String mensagem) {
        super(mensagem);
    }
    public ProcessoNaoEcontradoException(String mensagem, String processoId) {
        this(String.format("Não foi encontrado o processo de código %d", processoId));
    }
}
