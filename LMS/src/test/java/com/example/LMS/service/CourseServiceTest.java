package com.example.LMS.service;

import com.example.LMS.model.Course;
import com.example.LMS.repository.CourseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    private CourseRepo courseRepository;
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        courseRepository = new CourseRepo();
        courseService = new CourseService();
        courseService.setCourseRepository(courseRepository);
    }

    @Test
    void testCreateCourse() {
        Course course = courseService.createCourse("Java Basics", "Learn Java from scratch", "instructor1");
        assertNotNull(course.getId());
        assertEquals("Java Basics", course.getTitle());
    }

    @Test
    void testGetAllCourses() {
        courseService.createCourse("Java Basics", "Learn Java", "instructor1");
        courseService.createCourse("Python Basics", "Learn Python", "instructor2");

        List<Course> courses = courseService.getAllCourses();
        assertEquals(2, courses.size());
    }
}
