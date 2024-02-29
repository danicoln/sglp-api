package com.sglp.sglp_api.domain.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document
public class Endereco {

    private String id;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
}
