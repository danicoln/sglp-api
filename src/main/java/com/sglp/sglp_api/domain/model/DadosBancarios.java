package com.sglp.sglp_api.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosBancarios {

    private Long id;

    @NotBlank
    private String agencia;
    private String digitoAgencia;

    @NotBlank
    private String contaCorrente;
    private String digitoContaCorrente;
}
