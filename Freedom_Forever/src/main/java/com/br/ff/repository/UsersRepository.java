package com.br.ff.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.ff.model.Users;

public interface UsersRepository extends MongoRepository<Users, String> {
    
    Users findByUsername(String username);
    
    Optional<Users> findById(String userId);
    
    Page<Users> findAll(Pageable pageable);
}

