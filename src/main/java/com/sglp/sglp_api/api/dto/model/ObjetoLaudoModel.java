package com.sglp.sglp_api.api.dto.model;

import com.sglp.sglp_api.domain.model.Documento;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ObjetoLaudoModel {

    private Long id;
    private List<Documento> documentos;
}
