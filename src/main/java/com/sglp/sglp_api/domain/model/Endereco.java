package com.sglp.sglp_api.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco {

    private Long id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
}
