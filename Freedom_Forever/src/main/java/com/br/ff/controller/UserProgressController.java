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

import com.br.ff.model.UserProgress;
import com.br.ff.model.Users;
import com.br.ff.services.UserProgressService;


@RestController
@RequestMapping("/tracking")
public class UserProgressController {
	@Autowired
	private UserProgressService userProgressService;

    @PostMapping("/")
    public ResponseEntity<UserProgress> registerProgress(@RequestBody UserProgress progress) {
        return ResponseEntity.ok(userProgressService.registerProgress(progress));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserProgress>> getUserProgress(@PathVariable Users userId) {
        return ResponseEntity.ok(userProgressService.getUserProgress(userId));
    }
    
//    @GetMapping("/{userId}/recommendation")
//    public ResponseEntity<String> getAiRecommendation(@PathVariable Users userId) {
//        return ResponseEntity.ok(userProgressService.getAiRecommendation(userId));
//    }
}
