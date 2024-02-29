package com.sglp.sglp_api.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document
public class DadosBancarios {

    private String id;

    private String nomeBanco;
    @NotBlank
    private String agencia;
    private String digitoAgencia;

    @NotBlank
    private String contaCorrente;
    private String digitoContaCorrente;
}
