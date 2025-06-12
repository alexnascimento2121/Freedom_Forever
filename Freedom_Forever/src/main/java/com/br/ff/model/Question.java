package com.br.ff.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.br.ff.model.Enum.QuestionType;

@Document(collection = "questions")
public class Question {

    @Id
    private String id;

    private String questionText;

    private QuestionType type;

    // Construtor padrão
    public Question() {}

    // Construtor com parâmetros
    public Question(String questionText, QuestionType type) {
        this.questionText = questionText;
        this.type = type;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}