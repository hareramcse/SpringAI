package com.hs.spring_ai_prompt.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

	@Bean(name = "chatClient")
	public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
		return chatClientBuilder.defaultSystem("You are a helpful coding assistant. You are an expert in coding.")
				.defaultOptions(
						OpenAiChatOptions.builder().model("gpt-4o-mini").temperature(0.5).maxTokens(100).build())
				.build();
	}
}
