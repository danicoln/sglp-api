package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ObjetoLaudoInput {

    private String id;
    private String exameDaMateriaId;
    private DocumentoInput documento;

}
