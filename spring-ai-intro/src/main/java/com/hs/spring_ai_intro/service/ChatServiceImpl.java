package com.hs.spring_ai_intro.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatClient chatClient;

	@Override
	public String chat(String query) {
		return chatClient.prompt(query).call().content();
	}

}