package com.sglp.sglp_api.domain.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExameDaMateria {

    private String id;
    private List<ObjetoLaudo> objetos;
    private String descricao;
}
