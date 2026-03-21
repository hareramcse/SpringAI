package com.hs.spring_ai_rag.service;

import java.util.List;

import reactor.core.publisher.Flux;

public interface ChatService {

	String chatTemplate(String query, String userId);

	Flux<String> streamChat(String query);

	void saveData(List<String> list);

}
