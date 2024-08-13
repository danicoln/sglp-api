package com.sglp.sglp_api.domain.model;

import lombok.Getter;

@Getter
public enum Parte {

    AUTOR("Autor"),
    REQUERENTE("Requerente"),
    EXEQUENTE("Exequente"),
    EMBARGANTE("Embargante"),
    REU("Réu"),
    REQUERIDO("Requerido"),
    EXECUTADO("Executado"),
    EMBARGADO("Embargado");

    private final String descricao;

    Parte(String descricao) {
        this.descricao = descricao;
    }
}
