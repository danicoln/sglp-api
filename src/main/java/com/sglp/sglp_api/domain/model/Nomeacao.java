package com.sglp.sglp_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "nomeacoes")
public class Nomeacao {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private LocalDateTime dataNomeacao;
    private Processo processo;
    private String aceite;
    private LocalDateTime dataAceite;
    private LocalDateTime prazo;
}