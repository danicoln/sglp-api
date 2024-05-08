package com.sglp.sglp_api.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "laudos")
public class LaudoPericial {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    @NotNull
    private Processo processo;
    @NotBlank
    private String objetivo;
    @NotBlank
    private List<ObjetoLaudo> objetos;
    @NotBlank
    private String metodologiaAplicada;
    @NotBlank
    private ExameDaMateria exameDaMateria;
    @NotBlank
    private String historico;
    private List<Quesito> quesitos;
    @NotBlank
    private String conclusao;
    @NotBlank
    private String introducao;
    private LocalDateTime dataDoLaudo;
}
