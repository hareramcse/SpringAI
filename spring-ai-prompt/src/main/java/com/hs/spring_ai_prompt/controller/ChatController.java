package com.hs.spring_ai_prompt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hs.spring_ai_prompt.service.ChatService;

@RestController
public class ChatController {

	@Autowired
	private ChatService chatService;

	@GetMapping("/chat")
	public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String q) {
		var resultResponse = chatService.chat(q);
		return ResponseEntity.ok(resultResponse);
	}

	@GetMapping("/chatResource")
	public ResponseEntity<String> chatResource() {
		var resultResponse = chatService.chatResourceTemplate();
		return ResponseEntity.ok(resultResponse);
	}

}
