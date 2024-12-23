package com.example.LMS.model;

import java.util.List;
import java.util.Map;

public class Admin extends  User {
    private Map<Long, List<String>> courseEnrolledStudents; // Course ID -> List of student names

    public Admin(String name, String email, String password, String role) {
        super(name, email, password, role);
    }

    public Map<Long, List<String>> getCourseEnrolledStudents() {
        return courseEnrolledStudents;
    }

    public void setCourseEnrolledStudents(Map<Long, List<String>> courseEnrolledStudents) {
        this.courseEnrolledStudents = courseEnrolledStudents;
    }
       
}
