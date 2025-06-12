package com.br.ff.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.ff.model.Recommendation;
import com.br.ff.model.Users;

public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
    List<Recommendation> findByUserId(String userId);
    List<Recommendation> findByUser(Users user);
}
