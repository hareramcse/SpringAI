package com.hs.spring_ai_adviser.service;

import reactor.core.publisher.Flux;

public interface ChatService {

	String chatTemplate(String query);

	Flux<String> streamChat(String query);

}
