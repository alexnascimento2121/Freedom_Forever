package com.br.ff.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.ff.model.Answer;
import com.br.ff.model.Users;

public interface AnswerRepository extends MongoRepository<Answer, String> {
    
    List<Answer> findByUsers(Users user);
    
    List<Answer> findByQuestionId(String questionId);
    
    Optional<Answer> findByUsersIdAndQuestionId(String userId, String questionId);
    
    boolean existsByUsersId(String userId);
}
