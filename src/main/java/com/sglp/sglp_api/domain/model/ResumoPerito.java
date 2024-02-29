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
@Document(collection = "resumoPerito")
public class ResumoPerito extends Pessoa {

    @EqualsAndHashCode.Include
    @Id
    private String id;
}
