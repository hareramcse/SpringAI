package com.hs.spring_ai_rag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hs.spring_ai_rag.service.ChatService;

import reactor.core.publisher.Flux;

@RestController
public class ChatController {

	@Autowired
	private ChatService chatService;

	@GetMapping("/chat")
	public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String q,
			@RequestHeader("userId") String userId) {
		return ResponseEntity.ok(chatService.chatTemplate(q, userId));
	}

	@GetMapping("/stream-chat")
	public ResponseEntity<Flux<String>> streamChat(@RequestParam("q") String query) {
		return ResponseEntity.ok(this.chatService.streamChat(query));
	}

}
