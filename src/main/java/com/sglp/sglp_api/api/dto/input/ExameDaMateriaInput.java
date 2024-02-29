package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExameDaMateriaInput {

    private List<ObjetoLaudoInput> objetos;
    private String descricao;
}
