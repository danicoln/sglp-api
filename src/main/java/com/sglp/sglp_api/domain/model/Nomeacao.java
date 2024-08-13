package com.sglp.sglp_api.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "nomeacoes")
public class Nomeacao {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    private LocalDateTime dataNomeacao;
    @NotNull
    private Processo processo;
    private Boolean aceite;
    private LocalDateTime dataAceite;
    private LocalDateTime prazo;
    private BigDecimal honorarioHomologado;
    private BigDecimal honorarioEnviado;


}