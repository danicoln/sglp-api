package com.sglp.sglp_api.api.dto.input;

import com.sglp.sglp_api.api.dto.model.LaudoPericialIdInput;
import com.sglp.sglp_api.api.dto.model.NomeacaoIdInput;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PeritoInput {

    private String nome;
    private String cpf;
    private DadosPeritoInput dadosPerito;
    private List<NomeacaoIdInput> nomeacoes;
    private List<LaudoPericialIdInput> laudosPericiais;

}
