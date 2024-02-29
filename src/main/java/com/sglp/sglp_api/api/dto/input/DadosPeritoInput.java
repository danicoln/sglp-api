package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosPeritoInput {

    private String ccm;
    private String pis;
    private String rg;
    private DadosBancariosInput dadosBancarios;
    private EnderecoInput endereco;
}
