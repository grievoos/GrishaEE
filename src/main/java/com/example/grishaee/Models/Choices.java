package com.example.grishaee.Models;

public class Choices {
    private Long id, questionId, userId;
    private String userChoice;
    private Question question;
    private User user;

    //  Конструкторы

    public Choices() {
    }

    public Choices(Long id, Long questionId, Long userId, String userChoice, Question question, User user) {
        this.id = id;
        this.questionId = questionId;
        this.userId = userId;
        this.userChoice = userChoice;
        this.question = question;
        this.user = user;
    }

    public Choices(Long questionId, Long userId, String userChoice, Question question, User user) {
        this.questionId = questionId;
        this.userId = userId;
        this.userChoice = userChoice;
        this.question = question;
        this.user = user;
    }

    public Choices(String userChoice, Question question, User user) {
        this.userChoice = userChoice;
        this.question = question;
        this.user = user;
    }
    //  Гетеры

    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserChoice() {
        return userChoice;
    }

    public Question getQuestion() {
        return question;
    }

    public User getUser() {
        return user;
    }

    //  Сетеры

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //  toString

    @Override
    public String toString() {
        return "Choice{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", userId=" + userId +
                ", userChoice='" + userChoice + '\'' +
                ", question=" + question +
                ", user=" + user +
                '}';
    }
}
