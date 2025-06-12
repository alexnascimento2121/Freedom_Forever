package com.br.ff.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.br.ff.model.Answer;
import com.br.ff.model.Question;
import com.br.ff.model.Recommendation;
import com.br.ff.model.Users;
import com.br.ff.repository.AnswerRepository;
import com.br.ff.repository.QuestionRepository;
import com.br.ff.repository.RecommendationRepository;
import com.br.ff.repository.UsersRepository;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private AILLamaRecommendationService llamaService;

    public List<Answer> getAnswersByQuestion(String questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public List<Answer> getAnswersByUser(String userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        return answerRepository.findByUsers(user);
    }

    public Answer createAnswer(String questionId, String userId, String answerText) {
        validarParametros(questionId, userId, answerText);

        Users user = usersRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));

        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new RuntimeException("Pergunta não encontrada com ID: " + questionId));

        if (answerRepository.findByUsersIdAndQuestionId(userId, questionId).isPresent()) {
            throw new RuntimeException("Usuário já respondeu essa pergunta.");
        }

        Answer answer = new Answer(null, question, user, answerText.trim());
        answer = answerRepository.save(answer);

        generateRecommendationForUser(user);

        return answer;
    }

    private void validarParametros(String questionId, String userId, String answerText) {
        if (questionId == null || userId == null) {
            throw new IllegalArgumentException("ID da pergunta e do usuário são obrigatórios.");
        }

        if (answerText == null || answerText.trim().isEmpty()) {
            throw new IllegalArgumentException("A resposta não pode estar vazia.");
        }
    }

    @Async
    private void generateRecommendationForUser(Users user) {
        List<Answer> userAnswers = answerRepository.findByUsers(user);

        StringBuilder prompt = new StringBuilder("Com base nas respostas abaixo, sugira um tratamento para dependência:\n\n");
        for (Answer a : userAnswers) {
            prompt.append("Pergunta: ").append(a.getQuestion().getQuestionText()).append("\n");
            prompt.append("Resposta: ").append(a.getAnswerText()).append("\n\n");
        }

        String recommendationText = llamaService.simplePrompt(prompt.toString());

        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);
        recommendation.setRecommendationText(recommendationText);

        recommendationRepository.save(recommendation);
    }

    public boolean hasUserAnswered(String userId) {
        return answerRepository.existsByUsersId(userId);
    }
}
