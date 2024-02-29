package com.sglp.sglp_api.api.dto.input;

import com.sglp.sglp_api.domain.model.Parte;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessoInput {

    private String numero;
    private String comarca;
    private String vara;
    private Parte parteAutora = Parte.AUTOR;
    private Parte parteReu = Parte.REU;
    private String assunto;
}
