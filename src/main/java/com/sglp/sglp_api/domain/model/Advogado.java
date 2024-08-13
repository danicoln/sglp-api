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
@Document(collection = "advogados")
public class Advogado {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String nome;
    private String email;
    private String telefone;
}
