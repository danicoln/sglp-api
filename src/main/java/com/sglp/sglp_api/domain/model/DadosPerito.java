package com.sglp.sglp_api.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosPerito {

    private Long id;

    @NotBlank
    private String ccm;
    @NotBlank
    private String pis;
    @NotBlank
    private String rg;
    @NotBlank
    private DadosBancarios dadosBancarios;

    private Endereco endereco;
}
