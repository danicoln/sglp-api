package com.sglp.sglp_api.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quesito {

    private Long id;
    private Parte parte;
    private String quesito;
}
