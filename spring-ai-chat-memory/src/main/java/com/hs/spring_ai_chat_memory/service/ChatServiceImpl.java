package com.hs.spring_ai_chat_memory.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatClient chatClient;

	@Value("classpath:/prompts/user-message.st")
	private Resource userMessage;

	@Value("classpath:/prompts/system-message.st")
	private Resource systemMessage;

	@Override
	public String chatTemplate(String query, String userId) {

		return chatClient.prompt().advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, userId))
				.advisors(new SimpleLoggerAdvisor()).system(system -> system.text(systemMessage))
				.user(user -> user.text(userMessage).param("concept", query)).call().content();
	}

	@Override
	public Flux<String> streamChat(String query) {
		return chatClient.prompt().system(system -> system.text(systemMessage))
				.user(user -> user.text(userMessage).param("concept", query)).stream().content();

	}

}
