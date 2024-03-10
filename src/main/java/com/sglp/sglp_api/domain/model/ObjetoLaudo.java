package com.sglp.sglp_api.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "objetos")
public class ObjetoLaudo {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private List<Documento> documentos;
}
