package com.example.LMS.service;

import com.example.LMS.model.Course;
import com.example.LMS.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepository;

    public Course createCourse(String title, String description, String instructorId) {
        Course course = new Course(title, description, instructorId);
        return courseRepository.save(course);
    }

    public Optional<Course> getCourseById(String id) {
        return courseRepository.findById(id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
