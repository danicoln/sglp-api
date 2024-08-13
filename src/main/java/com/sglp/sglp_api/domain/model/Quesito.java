package com.sglp.sglp_api.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "quesitos")
public class Quesito {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String parte;
    private String pergunta;
    private String resposta;
    private String laudoId;
}
