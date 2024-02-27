package com.sglp.api.domain.model;

import jakarta.persistence.OneToOne;

public class Pessoa {

    private String nome;
    private String cpf;

    @OneToOne
    private DadosPessoa dadosPessoa;
}