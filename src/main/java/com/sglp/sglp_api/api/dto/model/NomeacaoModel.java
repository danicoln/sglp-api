package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NomeacaoModel {

    private String id;
    private ProcessoModel processo;
    private LocalDateTime dataNomeacao;
}
