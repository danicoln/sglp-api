package com.sglp.sglp_api.domain.service;

import com.sglp.sglp_api.api.dto.input.ChatGPTRequest;
import com.sglp.sglp_api.api.dto.model.ChatGPTResponse;
import com.sglp.sglp_api.domain.service.strategy.GPTStrategy;
import com.sglp.sglp_api.domain.service.strategy.QuesitoStrategy;
import org.springframework.ai.openai.api.common.OpenAiApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IAService {

    public static final String DADOS_INVALIDOS = "Dados inválidos na requisição";
    public static final String SEM_RESPOSTA_PARA_A_IA = "Sem resposta para a IA";
    public static final String RESP_PORTUGUES_BR = "Responda em português brasileiro: ";

    @Value("${spring.ai.openai.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public IAService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String processChatRequest(ChatGPTRequest request) {
        validateRequest(request);
        String prompt = RESP_PORTUGUES_BR + request.getMessages().get(0).getContent();
        request.getMessages().get(0).setContent(prompt);
        ChatGPTResponse chatGPTResponse = restTemplate.postForObject(apiUrl, request, ChatGPTResponse.class);
        return validateResponse(chatGPTResponse);
    }

    public String processEntityRequest(ChatGPTRequest request, String prompt ) {
        validateRequest(request);
        request.getMessages().get(0).setContent(prompt);
        ChatGPTResponse chatGPTResponse = restTemplate.postForObject(apiUrl, request, ChatGPTResponse.class);
        return validateResponse(chatGPTResponse);
    }

    private static String validateResponse(ChatGPTResponse response) {
        if (response != null && !response.getChoices().isEmpty()) {
            return response.getChoices().get(0).getMessage().getContent();
        } else {
            throw new OpenAiApiException(SEM_RESPOSTA_PARA_A_IA);
        }
    }

    private static void validateRequest(ChatGPTRequest request) {
        if (request == null
                || request.getMessages() == null
                || request.getMessages().isEmpty()
                || request.getMessages().get(0).getContent() == null) {
            throw new IllegalArgumentException(DADOS_INVALIDOS);
        }
    }

}
