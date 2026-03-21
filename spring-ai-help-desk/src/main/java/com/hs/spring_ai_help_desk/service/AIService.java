package com.hs.spring_ai_help_desk.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.hs.spring_ai_help_desk.tools.EmailTool;
import com.hs.spring_ai_help_desk.tools.TicketDatabaseTool;

import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Flux;

@Getter
@Setter
@Service
public class AIService {

	@Autowired
	private ChatClient chatClient;

	@Autowired
	private TicketDatabaseTool ticketDatabaseTool;

	@Autowired
	private EmailTool emailTool;

	@Value("classpath:static/helpdesk-system.st")
	private Resource systemPromptResource;

	public String getResponseFromAssistant(String query, String conversationId) {
		return this.chatClient.prompt()
				.advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
				.tools(ticketDatabaseTool, emailTool).system(systemPromptResource).user(query).call().content();

	}

	public Flux<String> streamResponseFromAssistant(String query, String conversationId) {
		return this.chatClient.prompt()
				.advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
				.tools(ticketDatabaseTool, emailTool).system(systemPromptResource).user(query).stream().content();

	}

}
