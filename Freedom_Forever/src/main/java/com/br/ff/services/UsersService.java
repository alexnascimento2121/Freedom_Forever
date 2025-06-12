package com.br.ff.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.br.ff.model.Users;
import com.br.ff.repository.UsersRepository;

@Service
public class UsersService {
    
    @Autowired
    private UsersRepository repository;

    public Users findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    public Users createOrUpdate(Users users) {
        return this.repository.save(users);
    }

    public Optional<Users> findById(String id) {
        return this.repository.findById(id);
    }

    public void deleteByUsername(String username) {
        Users userToDelete = this.repository.findByUsername(username);
        if (userToDelete != null) {
            repository.delete(userToDelete); // Deleta o usuário encontrado
        } else {
            throw new RuntimeException("Usuário não encontrado para exclusão com username: " + username);
        }
    }

    public Page<Users> findAll(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return repository.findAll(pageable);
    }
}

