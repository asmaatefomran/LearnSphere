package com.example.LMS.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class lesson {
    String name;
    List<Question> questions;
    List<Student> attendees;
    String instructorId;
    public boolean isStudentAttending(Student student) {
        return attendees.contains(student);
    }
}
