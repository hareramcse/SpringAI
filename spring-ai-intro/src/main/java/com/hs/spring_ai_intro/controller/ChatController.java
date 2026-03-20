package com.hs.spring_ai_intro.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

	@Autowired
	private ChatClient openAiChatClient;

	@GetMapping("/chat")
	public ResponseEntity<String> chat(@RequestParam(value = "q", required = true) String q) {
		var resultResponse = openAiChatClient.prompt(q).call().content();
		return ResponseEntity.ok(resultResponse);
	}

}