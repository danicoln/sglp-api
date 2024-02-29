package com.sglp.sglp_api.api.dto.input;

import com.sglp.sglp_api.domain.model.Nomeacao;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PeritoInput {

    private String nome;
    private String cpf;
    private DadosPeritoInput dadosPerito;
    private List<NomeacaoInput> nomeacoes;
    private List<LaudoPericialInput> laudosPericiais;

}
