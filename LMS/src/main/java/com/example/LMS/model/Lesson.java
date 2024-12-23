package com.example.LMS.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    String name;
    List<Question> questions = new ArrayList<>();
    List<Student> attendees = new ArrayList<>();
    String instructorId;
    Long CourseId;

    public Lesson(String name, long l) {
this.name = name;
this.CourseId = l;
    }

    public boolean isStudentAttending(Student student) {
        return attendees.contains(student);
    }
}
