package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DocumentoInput {

    private String nomeTitulo;
    private String descricao;
    private LocalDateTime data;
}
