package com.example.LMS.model;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class Course {
    private String id;        
    private String title;    
    private String description; 
    private String instructorId; 
    private List<String> enrolledStudentIds; 

    public Course(String title, String description, String instructorId) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.enrolledStudentIds = new ArrayList<>();
    }

}
