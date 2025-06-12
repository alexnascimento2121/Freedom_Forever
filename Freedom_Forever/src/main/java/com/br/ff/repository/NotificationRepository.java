package com.br.ff.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.ff.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByUser_IdAndReadFalse(String userId);
}

