package com.sglp.sglp_api.domain.exception;

public class ExameDaMateriaNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ExameDaMateriaNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public ExameDaMateriaNaoEncontradoException(String mensagem, String exameId) {
        this(String.format("Não existe exame da matéria com o código %d", exameId));
    }
}
