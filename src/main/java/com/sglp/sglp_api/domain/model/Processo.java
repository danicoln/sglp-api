package com.sglp.sglp_api.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "processos")
public class Processo {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    @NotNull
    private String numero;
    private String comarca;
    private String vara;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Parte parteAutora;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Parte parteReu;
    private String nomeAutor;
    private String nomeReu;
    private String assunto;
    private Advogado advogadoAutor;
    private Advogado advogadoReu;

    private String laudoId;
}
