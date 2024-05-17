package com.sglp.sglp_api.api.dto.input;

import com.sglp.sglp_api.domain.model.Processo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class LaudoPericialInput {

    @NotNull
    private ProcessoInput processo;
//    @NotBlank
    private String objetivo;
//    @NotBlank
    private String metodologiaAplicada;
//    @NotBlank
    private String conclusao;
//    @NotBlank
    private String introducao;
    private OffsetDateTime dataDoLaudo;
//    @NotBlank
    private String historico;

//    private List<QuesitoInput> quesitos;
//    private List<ObjetoLaudoInput> objetos;
//    private ExameDaMateriaInput exameDaMateria;
}
