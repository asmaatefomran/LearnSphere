package com.example.LMS.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student extends User {
    private String major;
    private float GPA;
    private int GraduationYear;
    private List<Course> courses;


}
