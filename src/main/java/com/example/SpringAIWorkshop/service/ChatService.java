package com.example.SpringAIWorkshop.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	//1. Field injection
	// @Autowired
	// private ChatModel chatModel;

	//2. Constructor injection (recommended)
	private final ChatModel chatModel;
    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String askAI(String prompt) {
    	
    	OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model("gpt-5.1")
                .maxCompletionTokens(500)
                .temperature(0.5)
                .build();
    	
        ChatResponse response = chatModel.call(new Prompt(prompt, options));
      
        return response.getResult().getOutput().getText();
    }
}
