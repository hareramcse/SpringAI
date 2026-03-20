package com.hs.spring_ai_chat_memory.service;

import reactor.core.publisher.Flux;

public interface ChatService {

	String chatTemplate(String query, String userId);

	Flux<String> streamChat(String query);

}
