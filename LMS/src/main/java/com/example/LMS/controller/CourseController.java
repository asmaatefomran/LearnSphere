package com.example.LMS.controller;

import java.util.List;
import java.util.Optional;

import com.example.LMS.model.Lesson;
import com.example.LMS.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.LMS.model.Course;
import com.example.LMS.model.Instructor;
import com.example.LMS.service.CourseService;
import com.example.LMS.service.UserService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    // Endpoint for creating a course
    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody Course course) {
        Optional<User> instructor = userService.findById(Long.parseLong(course.getInstructorId()));
        if (instructor.isPresent()) {
            User user = userService.getUserbyId(Long.parseLong(course.getInstructorId()));
            if (user.getRole().equalsIgnoreCase("instructor")) {
                Course createdCourse = courseService.createCourse(
                        course.getTitle(),
                        course.getDescription(),
                        Long.parseLong(course.getInstructorId()));

                // instructor.setCourseTaughtID(course.getId());
                return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse.toString());
            }

        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("there is no instructor with that id");
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
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        boolean isUpdated = courseService.updateCourse(id, updatedCourse);
        if (isUpdated) {
            return ResponseEntity.ok(updatedCourse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/addlesson")
    public ResponseEntity<String> addLesson(@RequestParam Long courseID, @RequestParam Long InstId,
            @RequestBody Lesson lesson) {
        Optional<Course> course = courseService.getCourseById(courseID);
        if (course.isPresent()) {
            if (Long.parseLong(course.get().getInstructorId())==(InstId)) {
                lesson.setCourseId(courseID);
                Optional<Lesson> lesson1 = courseService.addLesson(lesson);
                return ResponseEntity.status(HttpStatus.CREATED).body(lesson1.toString());
            }
            return ResponseEntity.ok().body("You donnot have the right to add lesson to that course");
        }
        return ResponseEntity.ok().body("there is no course with that id");

    }

    
}
