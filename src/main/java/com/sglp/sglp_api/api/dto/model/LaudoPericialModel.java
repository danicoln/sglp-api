package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class LaudoPericialModel {

    private String id;
    private String objetivo;
    private List<ObjetoLaudoModel> objetos;
    private String metodologiaAplicada;
    private ExameDaMateriaModel exameDaMateria;
    private String historico;
    private List<QuesitoModel> quesitos;
    private String conclusao;
    private String introducao;
    private OffsetDateTime dataDoLaudo;
}
