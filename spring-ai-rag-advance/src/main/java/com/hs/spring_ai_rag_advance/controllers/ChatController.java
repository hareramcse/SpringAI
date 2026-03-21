package com.hs.spring_ai_rag_advance.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hs.spring_ai_rag_advance.service.ChatService;

@RestController
@RequestMapping
public class ChatController {

    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }


    @GetMapping("/chat")
    public ResponseEntity<String> getResponse(@RequestParam("q") String userQuery){
        return ResponseEntity.ok(chatService.getResponse(userQuery));
    }





}
