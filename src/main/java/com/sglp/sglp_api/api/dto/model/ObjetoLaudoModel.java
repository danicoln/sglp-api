package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ObjetoLaudoModel {

    private Long id;
    private List<DocumentoModel> documentos;
}
