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
        this.students=new HashMap<>();
        id = 0L;
        this.setId();
    }

    public void setId() {
        id++;
    }

    @Override
    public String toString() {
        return "Quiz [ questions=" + questions;
    }

    public void TakeQuiz(Long studId) {
        students.put(studId, 0);
    }

    public Integer gradeQuiz(Long studId, List<String> answers) {
        Integer total = 0;

        for (int i = 0; i < questions.size(); i++) {
            System.out.println(answers.get(i));
            System.out.println(questions.get(i).getModelAnswer());
            if (answers.get(i) == questions.get(i).getModelAnswer()) {
                total += questions.get(i).getGrade();
            }
        }
        students.put(studId, total);
        return total;
    }

}
