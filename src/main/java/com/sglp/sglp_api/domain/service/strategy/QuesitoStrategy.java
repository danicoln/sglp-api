package com.sglp.sglp_api.domain.service.strategy;

import com.sglp.sglp_api.api.dto.input.ChatGPTRequest;
import org.springframework.stereotype.Service;

@Service
public class QuesitoStrategy implements GPTStrategy {

    public static final String RESP_PORTUGUES_BR = "Responda em português brasileiro: ";

    @Override
    public String buildPrompt(ChatGPTRequest request) {
        String fieldValue = request.getMessages().get(0).getContent();
        return RESP_PORTUGUES_BR +
                "Forneça uma resposta com base na informação: " + fieldValue +
                ". Inicie a resposta: 'Afirmativa a resposta' caso a resposta seja positiva, " +
                "'Negativa a resposta' se for negativa, " +
                "'Prejudicada a resposta' se não tiver fundamentos suficientes para resposta " +
                "ou se a pergunta fugir do objeto da perícia." +
                "Em seguida responda o motivo ou fundamento da resposta.";
    }

}
