package com.example.LMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.model.Course;
import com.example.LMS.model.Student;
import com.example.LMS.model.User;
import com.example.LMS.repository.CourseRepo;
import com.example.LMS.repository.StudentRepo;
import com.example.LMS.repository.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InstructorService {
    private UserService userService;
    private UserRepo userRepo;
    @Autowired
    private CourseService courseService;
    public List<User> viewStudentInCourse(String instructorId,long cid){
    Optional<Course>courses =courseService.getCourseById(cid);
    if(courses.isEmpty()|| !courses.get().getInstructorId().equals(instructorId)){
        throw new IllegalArgumentException("No courses found for this instructor or no course by this id ");

    }
    List<String> enrolledStudentIds = courses.get().getEnrolledStudentIds();
    System.out.println(enrolledStudentIds);
        return courseService.getEnrolledStudentIds(enrolledStudentIds);
    }

}
