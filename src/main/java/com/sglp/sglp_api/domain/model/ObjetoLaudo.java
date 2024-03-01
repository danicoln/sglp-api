package com.sglp.sglp_api.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ObjetoLaudo {

    private Long id;
    private List<Documento> documentos;
}
