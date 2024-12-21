package com.example.LMS.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
@Getter
@Setter
public class Assesment {
    Long id;
    List<HashMap<Long, Float>> students;
    List<Question> questions;
    int grade;
    String Feedback;
    String instructorId;
    Long CourseID;
    public long setId() {
        this.id = UserIdGenerotor.generateId();
        return this.id;
    }
}
