package com.sglp.api.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "processo")
public class Processo {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String comarca;
    private String vara;
    private Parte parteAutora = Parte.AUTOR;
    private Parte parteReu = Parte.REU;
    private String assunto;
}
