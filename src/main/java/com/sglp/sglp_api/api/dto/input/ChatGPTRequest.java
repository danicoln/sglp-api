package com.sglp.sglp_api.api.dto.input;

import com.sglp.sglp_api.domain.service.utils.Message;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequest {

    private String model;
    private List<Message> messages;

    public ChatGPTRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }
}
