package com.br.ff.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ff.model.UserProgress;
import com.br.ff.model.Users;
import com.br.ff.repository.UserProgressRepository;

@Service
public class UserProgressService {
	@Autowired
	private UserProgressRepository repository;
//	@Autowired
//	private  AIRecommendationService aiRecommendationService;

    public UserProgress registerProgress(UserProgress progress) {
        return repository.save(progress);
    }

    public List<UserProgress> getUserProgress(Users userId) {
        return repository.findByUsers(userId);
    }
    
//    // pega recomendacao direto do chatgpt
//    public String getAiRecommendation(Users userId) {
//        List<UserProgress> progressList = repository.findByUsers(userId);
//
//        if (progressList.isEmpty()) {
//            return "Ainda não há dados suficientes para recomendações.";
//        }
//
//        String progressSummary = progressList.stream()
//            .map(p -> "- " + p.getProgressDate() + ": " + p.getNotes())
//            .reduce("", (a, b) -> a + "\n" + b);
//
//        return aiRecommendationService.getAiRecommendation(progressSummary);
//    }

}
