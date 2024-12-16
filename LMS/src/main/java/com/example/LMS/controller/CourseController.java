package com.example.LMS.controller;

import com.example.LMS.model.Course;
import com.example.LMS.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Endpoint for creating a course
    @PostMapping
    public ResponseEntity<Course> createCourse(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String instructorId) {
        Course course = courseService.createCourse(title, description, instructorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    // Endpoint to get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint to retrieve all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}
