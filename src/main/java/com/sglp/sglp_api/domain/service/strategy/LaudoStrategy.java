package com.sglp.sglp_api.domain.service.strategy;

import com.sglp.sglp_api.api.dto.input.ChatGPTRequest;
import org.springframework.stereotype.Service;

@Service
public class LaudoStrategy implements GPTStrategy {

    public static final String TEXTO_ANALISE = "Como perito, forneça um texto-análise com base na informação: ";

    @Override
    public String buildPrompt(ChatGPTRequest request) {
        String fieldValue = request.getMessages().get(0).getContent();
        return getString() + fieldValue;
    }

    private static String getString() {
        return TEXTO_ANALISE;
    }
}
