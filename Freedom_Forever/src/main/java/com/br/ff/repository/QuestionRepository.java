package com.br.ff.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.ff.model.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {
	Optional<Question> findById(String id);	
	Question findFirstByOrderByIdAsc();
}
