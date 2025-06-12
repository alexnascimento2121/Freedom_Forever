package com.br.ff.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.ff.model.UserProgress;
import com.br.ff.model.Users;



public interface UserProgressRepository extends MongoRepository<UserProgress, String> {
    List<UserProgress> findByUsers(Users userId);
}
