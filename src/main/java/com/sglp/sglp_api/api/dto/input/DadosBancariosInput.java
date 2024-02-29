package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosBancariosInput {

    private String nomeBanco;
    private String agencia;
    private String digitoAgencia;
    private String contaCorrente;
    private String digitoContaCorrente;
}
