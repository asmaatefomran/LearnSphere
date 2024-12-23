package com.example.LMS.model;

import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Quiz {
    String type;// MCQ , true/flase , shortanswers
    Long Id;
    List<Question> questions;
    List<HashMap<Long, Integer>> students;// student id, grade

    public Quiz(String type, List<Question> questions) {
        this.type = type;
        this.questions = questions;
        Id = 0L;
    }

    @Override
    public String toString() {
        return "Quiz [type=" + type + ", questions=" + questions + ", students=" + students + "]";
    }

    public Long setId() {
        return Id++;
    }

}
