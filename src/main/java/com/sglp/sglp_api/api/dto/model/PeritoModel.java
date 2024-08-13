package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PeritoModel {

    private String id;
    private String nome;
    private List<NomeacaoResumoModel> nomeacoes;
    private List<LaudoPericialResumoModel> laudosPericiais;
    private boolean ativo;

}
