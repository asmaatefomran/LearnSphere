package com.example.LMS.model;

public class Question {
    String question;
    String modelAnswer;
    Integer grade;
    

    public Question(String question, String modelAnswer, Integer grade) {
        this.question = question;
        this.modelAnswer = modelAnswer;
        this.grade = grade;
    }

    public String getQuestion() {
        return question;
    }

    public String getModelAnswer() {
        return modelAnswer;
    }

    public Integer getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Question [question=" + question +", grade=" + grade + "]";
    }

}