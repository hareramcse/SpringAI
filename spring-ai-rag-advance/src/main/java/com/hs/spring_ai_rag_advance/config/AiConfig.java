package com.hs.spring_ai_rag_advance.config;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder.defaultAdvisors(new SimpleLoggerAdvisor(), new SafeGuardAdvisor(List.of("games")))
				.defaultOptions(
						OpenAiChatOptions.builder().model("gpt-4o-mini").temperature(0.3).maxTokens(200).build())
				.build();
	}

}
