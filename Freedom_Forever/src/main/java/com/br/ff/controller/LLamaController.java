package com.br.ff.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ff.controller.Request.ChatRequest;
import com.br.ff.services.LLamaService;

@RestController
@RequestMapping("/ollama")
public class LLamaController {
	private final LLamaService ollamaService = new LLamaService();
    

    @PostMapping("/chat")
    public ResponseEntity<String> simpleChat(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(ollamaService.simplePrompt(body.get("prompt")));
    }

    @PostMapping("/chat/history")
    public ResponseEntity<String> chatWithHistory(@RequestBody ChatRequest request) {
        return ResponseEntity.ok(ollamaService.chatWithHistory(request));
    }

    @PostMapping("/embedding")
    public ResponseEntity<String> embedding(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(ollamaService.embedding(body.get("text")));
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancel(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(ollamaService.cancel(body.get("model")));
    }
}
