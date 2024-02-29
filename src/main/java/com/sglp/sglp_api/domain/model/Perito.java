package com.sglp.sglp_api.domain.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "perito")
public class Perito extends Pessoa {

    private Long id;
    private List<Nomeacao> nomeacoes = new ArrayList<>();
    private List<LaudoPericial> laudosPericiais;

}
