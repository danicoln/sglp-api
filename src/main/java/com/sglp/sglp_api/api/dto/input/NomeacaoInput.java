package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NomeacaoInput {

    private String id;
    private LocalDateTime dataNomeacao;
    private ProcessoInput processo;
    private String aceite;
    private LocalDateTime dataAceite;
    private LocalDateTime prazo;
}
