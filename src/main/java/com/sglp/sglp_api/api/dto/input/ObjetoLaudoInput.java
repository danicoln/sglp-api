package com.sglp.sglp_api.api.dto.input;

import com.sglp.sglp_api.domain.model.Documento;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ObjetoLaudoInput {

    private List<Documento> documentos;
}
