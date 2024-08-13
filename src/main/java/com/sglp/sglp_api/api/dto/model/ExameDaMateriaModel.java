package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExameDaMateriaModel {

    private String id;
    private List<String> objetosIds;
    private String descricao;
}
