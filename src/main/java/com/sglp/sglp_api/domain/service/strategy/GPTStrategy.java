package com.sglp.sglp_api.domain.service.strategy;

import com.sglp.sglp_api.api.dto.input.ChatGPTRequest;

public interface GPTStrategy {
    String buildPrompt(ChatGPTRequest request);
}
