package com.sglp.sglp_api.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "nomeacoes")
public class Nomeacao {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private OffsetDateTime dataNomeacao;
    private Processo processo;
}