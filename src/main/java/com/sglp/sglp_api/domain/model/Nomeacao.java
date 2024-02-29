package com.sglp.sglp_api.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class Nomeacao {

    private Long id;

    private Perito perito;

    private OffsetDateTime dataNomeacao;

    private Processo processo;
}