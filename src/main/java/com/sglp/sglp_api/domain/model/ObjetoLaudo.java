package com.sglp.sglp_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "objetos")
public class ObjetoLaudo {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String exameDaMateriaId;
    private Documento documento;
}
