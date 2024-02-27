package com.sglp.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dados_pessoa")
public class DadosPessoa {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String ccm;
    @NotBlank
    private String pis;
    @NotBlank
    private String rg;
    @NotBlank
    @OneToMany
    private DadosBancarios dadosBancarios;

    @OneToMany
    private Endereco endereco;
}
