package com.sglp.sglp_api.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class LaudoPericialInput {

    @NotBlank
    private String objetivo;
    private List<ObjetoLaudoInput> objetos;
    @NotBlank
    private String metodologiaAplicada;
    private ExameDaMateriaInput exameDaMateria;
    @NotBlank
    private String historico;
    private List<QuesitoInput> quesitos;
    @NotBlank
    private String conclusao;
    @NotBlank
    private String introducao;
    private OffsetDateTime dataDoLaudo;
}
