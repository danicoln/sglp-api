package com.sglp.sglp_api.api.dto.input;

import com.sglp.sglp_api.domain.model.Parte;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuesitoInput {

    private String id;
    private Parte parte;
    private String pergunta;
    private String resposta;
    private String laudoId;
}
