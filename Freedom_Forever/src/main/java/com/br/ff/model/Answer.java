package com.br.ff.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "answers")
public class Answer {

    @Id
    private String id;

    @DBRef
    private Question question;

    @DBRef
    private Users users;

    private String answerText;

    public Answer() {}

    public Answer(String id, Question question, Users users, String answerText) {
        this.id = id;
        this.question = question;
        this.users = users;
        this.answerText = answerText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
