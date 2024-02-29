package com.sglp.sglp_api.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document
public class DadosPerito {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    @NotBlank
    private String ccm;
    @NotBlank
    private String pis;
    @NotBlank
    private String rg;
    @NotBlank
    private DadosBancarios dadosBancarios;

    private Endereco endereco;
}
