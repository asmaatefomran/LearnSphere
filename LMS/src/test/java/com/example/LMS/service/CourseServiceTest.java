package com.example.LMS.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.LMS.model.Course;
import com.example.LMS.repository.CourseRepo;

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
    @Test
void testDeleteCourseWhenFound() {
  
    Course course = courseService.createCourse("Java Basics", "Learn Java", "123");
    Long courseId = course.getId();

   
    boolean isDeleted = courseService.deleteCourse(courseId);

 
    assertTrue(isDeleted);
    //must not found after delete
    assertFalse(courseService.getCourseById(courseId).isPresent());
}

@Test
void testDeleteCourseWhenNotFound() {
    
    Long CourseId = 999L; // course not found 
    boolean isDeleted = courseService.deleteCourse(CourseId);
    assertFalse(isDeleted);
}
@Test
void testUpdateCoursewhenfound() {
    // Create a course to update
    Course course = courseService.createCourse("Java Basics", "Learn Java", "123");
    Long courseId = course.getId();

   
    Course updatedCourse=new Course("SoftWare2","Advanced Software","123");
    boolean isUpdated = courseService.updateCourse(courseId, updatedCourse);

    
    assertTrue(isUpdated);
    Course retrievedCourse = courseService.getCourseById(courseId).orElse(null);

    assertNotNull(retrievedCourse);
    assertEquals("SoftWare2", retrievedCourse.getTitle());
    assertEquals("Advanced Software", retrievedCourse.getDescription());
}
@Test
void testUpdateCourseWhenNotFound() {
   
    Long CourseId = 999L; 

    Course updatedCourse=new Course("SoftWare2","Advanced Software","123");
    boolean isUpdated = courseService.updateCourse(CourseId, updatedCourse);
    assertFalse(isUpdated);
}

}

