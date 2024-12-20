package com.example.LMS.repository;
import com.example.LMS.model.Course;
import com.example.LMS.model.Student;
import com.example.LMS.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentRepo {
    private final UserRepo userRepo;
    private final CourseRepo courseRepo;

    @Autowired
    public StudentRepo(UserRepo userRepo,CourseRepo courseRepo){

        this.userRepo = userRepo;
        this.courseRepo=courseRepo;
    }

    public List<Student> findAllStudents(){
        return userRepo.findAllUsers().stream().filter(Student.class::isInstance).map(Student.class::cast).collect(Collectors.toList());
    }

    //=============================================================
    public String EnrollInCourse(String studentId,long courseId){
        Optional<Course> courseOptional = courseRepo.findById(courseId);


        if (courseOptional.isEmpty()) {
            return "Course not found.";
        }

        Course course = courseOptional.get();

        // Add the student ID to the list of enrolled students
        List<String> enrolledStudentIds = course.getEnrolledStudentIds();


        if (!enrolledStudentIds.contains(studentId)) {
            enrolledStudentIds.add(studentId);

            // Update the course in the repository

          courseRepo.updateCourse(course);
            return "Student enrolled successfully.";
        } else {
            return "Student is already enrolled in this course.";
        }
    }
    //=============================================================
    public String UnEnrollFromCourse(String studentId,long courseId) {
        Optional<Course> courseOptional = courseRepo.findById(courseId);

        if (courseOptional.isEmpty()) {
            return "Course not found.";
        }
        Course course = courseOptional.get();

        // Get the list of enrolled students
        List<String> enrolledStudentIds = course.getEnrolledStudentIds();

        // Check if the student is currently enrolled
        if (enrolledStudentIds.contains(studentId)) {
            enrolledStudentIds.remove(studentId);
            // Update the course in the repository
           courseRepo.updateCourse(course);
            return "Student UnEnrolled successfully.";
        } else {
            return "Student is not enrolled in this course.";
        }
    }
//=============================================================

}
