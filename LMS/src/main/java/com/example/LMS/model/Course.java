package com.example.LMS.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Course {
    private Long id;
    private String title;
    private String description;

    private String instructorId;
    List<Assesment> assesments = new ArrayList<>();

    private List<String> enrolledStudentIds;
    private final List<Assesment> Assignments = new ArrayList<>();
    List<Question> questionBank;
    public List<Lesson> Lessons;


    public Course(String title, String description, Long instructorId) {

        this.id = CourseIdGenerator.generateId();
        this.title = title;
        this.description = description;
        this.instructorId = String.valueOf(instructorId);
        this.enrolledStudentIds = new ArrayList<>();
        this.Lessons = new ArrayList<>();
        this.questionBank = new ArrayList<>();
        
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
    public Course() {
        this.enrolledStudentIds = new ArrayList<>();
    }

    public void setAssesments(Assesment a) {
        this.assesments.add(a);
    }

    public List<Question> getRandomQuestions(){
        List<Question> rQuestions=new ArrayList<>();
        Random random = new Random();
        rQuestions.add( questionBank.get(random.nextInt(questionBank.size())));
        return rQuestions;
    }
}
