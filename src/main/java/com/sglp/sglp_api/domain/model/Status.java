package com.sglp.sglp_api.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    CONCLUIDO("CONCLUIDO", "Concluído"),
    AGUARDANDO("AGUARDANDO", "Aguardando"),
    NAO_INICIADO("NAO INICIADO", "Não iniciado"),
    EM_ANDAMENTO("EM ANDAMENTO", "Em andamento");

    public static final String CODIGO_INVALIDO = "Código inválido para Status: %s";
    private final String codigo;
    private final String descricao;

    public static Status getEnum(String codigo) {
        for (Status enumerador : Status.values()) {
            if (enumerador.codigo.equals(codigo)) {
                return enumerador;
            }
        }
        return null;
    }

    public static Status getByDescricao(String codigo) {
        for (Status enumerador : Status.values()) {
            if (enumerador.descricao.equals(codigo)) {
                return enumerador;
            }
        }
        return null;
    }

    @JsonCreator
    public static Status fromCodigo(String codigo) {
        System.out.println("Código recebido: " + codigo);
        for (Status status : Status.values()) {
            if (status.codigo.equalsIgnoreCase(codigo)) {
                return status;
            }
        }
        throw new IllegalArgumentException(CODIGO_INVALIDO + codigo);
    }
}

