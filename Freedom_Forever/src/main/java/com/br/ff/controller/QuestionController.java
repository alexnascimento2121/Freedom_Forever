package com.br.ff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.ff.model.Question;
import com.br.ff.services.QuestionService;


@RestController
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.createQuestion(question));
    }
    
    @GetMapping("/initial")
    public ResponseEntity<Question> getInitialQuestion() {
        Question question = questionService.getInitialQuestion(); // ex: primeira do banco
        return ResponseEntity.ok(question);
    }
}
