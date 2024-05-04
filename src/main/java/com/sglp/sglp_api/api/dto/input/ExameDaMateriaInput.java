package com.sglp.sglp_api.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExameDaMateriaInput {

    private String id;
    private List<String> objetosIds;
    private String descricao;
}
