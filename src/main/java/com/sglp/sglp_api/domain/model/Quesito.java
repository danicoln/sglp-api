package com.sglp.sglp_api.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quesito {

    private String id;
    private Parte parte;
    private String quesito;
    private String resposta;
}
