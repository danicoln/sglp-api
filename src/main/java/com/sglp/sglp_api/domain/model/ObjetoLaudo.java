package com.sglp.sglp_api.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ObjetoLaudo {

    private String id;
    private List<Documento> documentos;
}
