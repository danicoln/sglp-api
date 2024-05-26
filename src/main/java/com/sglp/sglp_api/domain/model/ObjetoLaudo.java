package com.sglp.sglp_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

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
    private String nomeTitulo;
    private String descricao;
    private LocalDateTime data;

    public void atualizarExame(ObjetoLaudo objetoLaudo) {
        this.setExameDaMateriaId(objetoLaudo.exameDaMateriaId);
        this.setNomeTitulo(objetoLaudo.getNomeTitulo());
        this.setDescricao(objetoLaudo.getDescricao());
        this.setData(objetoLaudo.getData());
    }
}
