package com.br.ff.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ff.model.Question;
import com.br.ff.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question getInitialQuestion() {
        Question question = questionRepository.findFirstByOrderByIdAsc();
        if (question == null) {
            throw new RuntimeException("Nenhuma pergunta encontrada");
        }
        return question;
    }
}
