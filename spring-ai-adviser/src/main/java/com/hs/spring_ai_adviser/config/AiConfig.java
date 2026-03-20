package com.hs.spring_ai_adviser.config;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hs.spring_ai_adviser.adviser.TokenPrintAdvisor;

@Configuration
public class AiConfig {

	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder.defaultAdvisors(new TokenPrintAdvisor(), new SafeGuardAdvisor(List.of("games")))
				.defaultSystem("You are a helpful coding assistant. You are an expert in coding.")
				.defaultOptions(
						OpenAiChatOptions.builder().model("gpt-4o-mini").temperature(0.6).maxTokens(200).build())
				.build();
	}

}
