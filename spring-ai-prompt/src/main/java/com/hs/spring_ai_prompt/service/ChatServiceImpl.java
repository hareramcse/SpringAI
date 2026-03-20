package com.hs.spring_ai_prompt.service;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

	@Value("classpath:/prompts/user-message.st")
	private Resource userMessage;

	@Value("classpath:/prompts/system-message.st")
	private Resource systemMessage;

	@Autowired
	private ChatClient chatClient;

	@Override
	public String chat(String query) {
		// modify this prompt and add extra things to prompt to make it more interactive
		String queryStr = "As an expert in coding and programing. Always write program in java . Now reply for this question :{query}";
		return chatClient.prompt().user(u -> u.text(queryStr).param("query", queryStr)).call().content();
	}

	@Override
	public String userTemplateChat(String query) {
		String prompt = "tell me about virat kohli?";
		Prompt prompt1 = new Prompt(query,
				OpenAiChatOptions.builder().model("gpt-4o-mini").temperature(0.3).maxTokens(100).build());

		return chatClient.prompt(prompt1).user(prompt).system("As as expert in cricket.").call().content();
	}

	@Override
	public String chatTemplate() {
		var systemPromptTemplate = SystemPromptTemplate.builder()
				.template("You are a helpful coding assistant. You are an expert in coding.").build();
		var systemMessage = systemPromptTemplate.createMessage();

		var userPromptTemplate = PromptTemplate.builder()
				.template("What is {techName}? tell ma also about {techExample}").build();
		var userMessage = userPromptTemplate
				.createMessage(Map.of("techName", "Spring", "techExample", "spring exception"));

		Prompt prompt = new Prompt(systemMessage, userMessage);
		return chatClient.prompt(prompt).call().content();
	}

	@Override
	public String chatResourceTemplate() {
		return chatClient.prompt().system(system -> system.text(systemMessage))
				.user(user -> user.text(userMessage).param("concept", "Python iteration")).call().content();
	}

}