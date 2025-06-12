package com.br.ff.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ff.model.Question;

@RestController
@RequestMapping("/api/treatment")
@CrossOrigin(origins = "*")
public class OnboardingController {	

	    @PostMapping("/recommend")
	    public Map<String, String> getRecommendation(@RequestBody Question question) {
	        Map<String, String> recommendation = new HashMap<>();
	        
	        // Simples lógica de recomendação
	        if (question.getQuestionText().equalsIgnoreCase("alcool")) {
	            recommendation.put("suggestion", "Considere procurar um grupo de apoio como AA.");
	        } else if (question.getQuestionText().equalsIgnoreCase("jogos")) {
	            recommendation.put("suggestion", "Tente estabelecer limites de tempo e buscar ajuda profissional.");
	        } else {
	            recommendation.put("suggestion", "Consulte um profissional para avaliar seu caso.");
	        }
	        return recommendation;
	    }
	

}
