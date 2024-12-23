package com.example.LMS.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Course {
    private Long id;
    private String title;
    private String description;
    private Long instructorId;
    private List<String> enrolledStudentIds;
    private final List<Assesment> Assignments;
    List<Question> questionBank;
    public List<Lesson> Lessons;

    public Course(String title, String description, Long instructorId) {
        this.id = CourseIdGenerator.generateId();
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.enrolledStudentIds = new ArrayList<>();
        this.Lessons = new ArrayList<>();
        this.questionBank = new ArrayList<>();
        this.Assignments = new ArrayList<>();
    }

    public void addAssigment(Assesment ass) {
        Assignments.add(ass);
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + ", description=" + description + ", instructorId="
                + instructorId + ", enrolledStudentIds=" + enrolledStudentIds + ", Assignments=" + Assignments
                + ", questionBank=" + questionBank + ", Lessons=" + Lessons + "]";
    }
    public Assesment getAssesment(Long id){
        
        return Assignments.stream().filter(assesment -> id.equals(assesment.getId())).findFirst().get();
    }

}
