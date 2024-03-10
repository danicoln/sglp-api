package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NomeacaoResumoModel {

    private ProcessoResumoModel processo;
    private LocalDateTime dataNomeacao;
}
