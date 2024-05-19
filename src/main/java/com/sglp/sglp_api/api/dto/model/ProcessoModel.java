package com.sglp.sglp_api.api.dto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessoModel {

    private String id;
    private String numero;
    private String comarca;
    private String vara;
    private String parteAutora;
    private String parteReu;
    private String nomeAutor;
    private String nomeReu;
    private String assunto;

    private String laudoId;
}
