package com.sglp.sglp_api.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class Documento {

    private Long id;

    private String nomeTitulo;

    private String descricao;

    private OffsetDateTime data;
}
