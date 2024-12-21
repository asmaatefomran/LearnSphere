package com.example.LMS.service;

import java.util.List;
import java.util.Optional;

import com.example.LMS.model.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.model.Course;
import com.example.LMS.repository.CourseRepo;

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

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public boolean deleteCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean updateCourse(Long id, Course updatedCourse) {

        Optional<Course> existingCourseOptional = courseRepository.findById(id);

        if (existingCourseOptional.isPresent()) {
            Course existingCourse = existingCourseOptional.get();


            if (updatedCourse.getTitle() != null) {
                existingCourse.setTitle(updatedCourse.getTitle());
            }
            if (updatedCourse.getDescription() != null) {
                existingCourse.setDescription(updatedCourse.getDescription());
            }
            if (updatedCourse.getInstructorId() != null) {
                existingCourse.setInstructorId(updatedCourse.getInstructorId());
            }

            return true;
        }

        return false;
    }

    public Optional<Lesson> addLesson(Lesson lesson){
        return Optional.ofNullable(courseRepository.addLesson(lesson.getCourseId(), lesson));
    }

}
