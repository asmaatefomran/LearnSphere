package com.example.LMS.model;

public class StudentGradeFeedback {
    private Float grade;
    private String feedback;

    // Getters and Setters
    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "StudentGradeFeedback{" +
                "grade=" + grade +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
