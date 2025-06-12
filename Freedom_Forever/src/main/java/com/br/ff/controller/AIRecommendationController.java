package com.br.ff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ff.controller.Request.RecommendationRequest;
import com.br.ff.model.Recommendation;
import com.br.ff.services.AILLamaRecommendationService;

@RestController
@RequestMapping("/ai/recommendations")
public class AIRecommendationController {
	
	@Autowired
	private AILLamaRecommendationService ollamaService;
	
	@PostMapping
    public ResponseEntity<Recommendation> generateRecommendation(@RequestBody RecommendationRequest request) {
        Recommendation recommendation = ollamaService.processAnswersAndGenerateRecommendation(request);
        return ResponseEntity.ok(recommendation);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendationsById(@PathVariable String userId) {
        return ResponseEntity.ok(ollamaService.getUserRecommendations(userId));
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<List<Recommendation>> getRecommendations(@PathVariable String username) {
        return ResponseEntity.ok(ollamaService.getUserRecommendations(username));
    }
}
