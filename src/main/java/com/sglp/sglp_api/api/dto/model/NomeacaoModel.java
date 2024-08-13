package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class NomeacaoModel {

    private String id;
    private ProcessoModel processo;
    private LocalDateTime dataNomeacao;
    private String aceite;
    private LocalDateTime dataAceite;
    private LocalDateTime prazo;
    private BigDecimal honorarioHomologado;
    private BigDecimal honorarioEnviado;
}
