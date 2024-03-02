package com.sglp.sglp_api.domain.model;

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
    private String numero;
    private String comarca;
    private String vara;
    private Parte parteAutora = Parte.AUTOR;
    private Parte parteReu = Parte.REU;
    private String assunto;
}
