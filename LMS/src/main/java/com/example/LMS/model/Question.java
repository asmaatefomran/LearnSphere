package com.example.LMS.model;

public class Question {
    String question;
    String answer;
    String modelAnswer;
    Integer grade;
    Integer mark;

    public Question(String question, String modelAnswer, Integer grade) {
        this.question = question;
        this.modelAnswer = modelAnswer;
        this.grade = grade;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getMark() {
        return mark;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getModelAnswer() {
        return modelAnswer;
    }

    public Integer getGrade() {
        return grade;
    }

}