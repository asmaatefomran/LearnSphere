package com.example.LMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.LMS.model.Lesson;
import com.example.LMS.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.model.Assesment;
import com.example.LMS.model.Course;
import com.example.LMS.repository.CourseRepo;
import com.example.LMS.repository.UserRepo;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepository;
    @Autowired
    private UserRepo ur;

    public Course createCourse(String title, String description, Long instructorId) {
        Course course = new Course(title, description, instructorId);
        return courseRepository.save(course);
    }

    public Optional<Course> getCourseById(Long id) {
        System.out.println("hh");
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
    public void addAssigment(Long courseId,Assesment assesment){

        courseRepository.addAssigment(courseId, assesment);;
    }
    public List<Assesment> viewAssesments(Long courseId){

        return courseRepository.viewAssesments(courseId);
    }

    public String view_course( long CourseID){
        return courseRepository.view_course(CourseID);
    }
    public List<User> getEnrolledStudentIds(List<String> enrolledStudentIds) {
           List<User> students = new ArrayList<>();
    for (String studentid : enrolledStudentIds) {
        User student = ur.getUserById(Long.parseLong(studentid));
        System.out.println(student);
        students.add(student);
        
    }
        return students;
}
    

}
