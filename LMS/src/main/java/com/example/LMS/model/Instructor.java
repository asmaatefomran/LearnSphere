package com.example.LMS.model;

import java.util.List;

public class Instructor extends User {

    public List<Course> getCoursesTaughtID() {
        return coursesTaughtID;
    }

    public void setCourseTaughtID(Course courseTaughtID) {
        coursesTaughtID.add(courseTaughtID);
    }

    private List<Course> coursesTaughtID;

}