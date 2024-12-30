package com.example.LMS.model;

import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor

@Getter

public class Quiz {
    Long courseId, id;
    List<Question> questions;
    HashMap<Long, Integer> students;// student id, grade

    public Quiz(List<Question> questions, long courseId) {
        this.questions = questions;
        this.courseId = courseId;
        id = 0L;
        this.setId();
    }

    public void setId() {
        id++;
    }

    @Override
    public String toString() {
        return "Quiz [ questions=" + questions + ", students=" + students + "]";
    }

    public void TakeQuiz(Long studId) {
        students.put(studId, 0);
    }

    public Integer gradeQuiz(Long studId) {
        Integer total = 0;
        for (Question question : questions) {
            if (question.getAnswer() == question.getModelAnswer()) {
                question.setMark(question.getGrade());
                total += question.getGrade();
            } else
                question.setMark(0);
        }
        students.put(studId, total);
        return total;
    }

}
