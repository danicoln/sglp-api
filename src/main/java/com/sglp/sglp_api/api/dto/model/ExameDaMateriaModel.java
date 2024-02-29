package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExameDaMateriaModel {

    private Long id;
    private List<ObjetoLaudoModel> objetos;
    private String descricao;
}
