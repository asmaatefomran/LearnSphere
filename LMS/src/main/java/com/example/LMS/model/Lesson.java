package com.example.LMS.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    String name;
    List<Question> questions;
    List<Student> attendees;
    String instructorId;
    Long CourseId;
    public boolean isStudentAttending(Student student) {
        return attendees.contains(student);
    }
}
