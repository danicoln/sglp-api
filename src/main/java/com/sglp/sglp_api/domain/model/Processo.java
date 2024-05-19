package com.sglp.sglp_api.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "processos")
public class Processo implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;
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

    private String laudoId;
}
