package com.sglp.sglp_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "peritos")
public class Perito extends Pessoa {

    @EqualsAndHashCode.Include
    @Id
    private String id;
    private List<Nomeacao> nomeacoes = new ArrayList<>();
    private List<LaudoPericial> laudosPericiais = new ArrayList<>();
    private DadosPerito dadosPerito;

}
