package com.hs.spring_ai_adviser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hs.spring_ai_adviser.service.ChatService;

import reactor.core.publisher.Flux;

@RestController
public class ChatController {

	@Autowired
	private ChatService chatService;

	@GetMapping("/chat")
	public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String q) {
		return ResponseEntity.ok(chatService.chatTemplate(q));
	}

	@GetMapping("/stream-chat")
	public ResponseEntity<Flux<String>> streamChat(@RequestParam("q") String query) {
		return ResponseEntity.ok(this.chatService.streamChat(query));
	}

}
