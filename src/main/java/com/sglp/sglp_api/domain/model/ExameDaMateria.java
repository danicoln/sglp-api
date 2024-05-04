package com.sglp.sglp_api.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "exames")
public class ExameDaMateria {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private List<String> objetosIds;
    private String descricao;
}
