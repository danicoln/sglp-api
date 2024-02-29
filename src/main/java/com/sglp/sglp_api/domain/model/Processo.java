package com.sglp.sglp_api.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Processo {

    private Long id;
    private String numero;
    private String comarca;
    private String vara;
    private Parte parteAutora = Parte.AUTOR;
    private Parte parteReu = Parte.REU;
    private String assunto;
}
