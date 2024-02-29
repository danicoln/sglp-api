package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class DocumentoModel {

    private String id;
    private String nomeTitulo;
    private String descricao;
    private OffsetDateTime data;
}
