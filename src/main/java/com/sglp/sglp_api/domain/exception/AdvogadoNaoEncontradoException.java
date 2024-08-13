package com.sglp.sglp_api.domain.exception;

import lombok.Getter;

@Getter
public class AdvogadoNaoEncontradoException extends RuntimeException {

    private final EntidadeNaoEncontradaException causaOriginal;

    public AdvogadoNaoEncontradoException(String advogadoId) {
        super("Advogado com ID " + advogadoId + " não encontrado.");
        this.causaOriginal = new EntidadeNaoEncontradaException(advogadoId);
    }

}
