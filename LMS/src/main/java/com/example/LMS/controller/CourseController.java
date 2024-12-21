package com.example.LMS.controller;

import java.util.List;
import java.util.Optional;

import com.example.LMS.model.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.LMS.model.Course;
import com.example.LMS.service.CourseService;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Endpoint for creating a course
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(
                course.getTitle(),
                course.getDescription(),
                course.getInstructorId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }
    // Endpoint to get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint to retrieve all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        boolean isDeleted = courseService.deleteCourse(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id,@RequestBody Course updatedCourse) {
        boolean isUpdated = courseService.updateCourse(id, updatedCourse);

        if (isUpdated) {
            return ResponseEntity.ok(updatedCourse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/addlesson")
    public ResponseEntity<Optional<Lesson>> addLesson(@RequestParam Long CourseID, @RequestBody Lesson lesson){
        lesson.setCourseId(CourseID);
        Optional<Lesson> lesson1 = courseService.addLesson(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(lesson1);



    }

}
