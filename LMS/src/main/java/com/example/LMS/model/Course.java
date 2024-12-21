package com.example.LMS.model;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class Course {
    private Long id;
    private String title;
    private String description;
    private String instructorId;
    private List<String> enrolledStudentIds;
    List<Question> questionBank;
    public List<lesson> Lessons;
    public Course(String title, String description, String instructorId) {
        this.id = CourseIdGenerator.generateId();
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.enrolledStudentIds = new ArrayList<>();
    }

}
