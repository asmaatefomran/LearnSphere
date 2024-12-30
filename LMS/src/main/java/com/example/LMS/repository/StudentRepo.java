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

        List<String> enrolledStudentIds = course.getEnrolledStudentIds();

        if (!enrolledStudentIds.contains(studentId)) {
            Student st = (Student) userRepo.getUserById(Long.parseLong(studentId));
            st.getCourses().add(course);
            enrolledStudentIds.add(studentId);
            courseRepo.updateCourse(course);
            System.out.println(st.getName());
            return "Student enrolled successfully.";
        } else {
            return "Student is already enrolled in this course.";
        }
    }
    //=============================================================
    public String UnEnrollFromCourse(Long studentId,long courseId) {
        Optional<Course> courseOptional = courseRepo.findById(courseId);

        if (courseOptional.isEmpty()) {
            return "Course not found.";
        }
        Course course = courseOptional.get();

        // Get the list of enrolled students
        List<String> enrolledStudentIds = course.getEnrolledStudentIds();

        // Check if the student is currently enrolled
        if (enrolledStudentIds.contains(String.valueOf(studentId))) {
            enrolledStudentIds.remove(String.valueOf(studentId));
            // Update the course in the repository
            Student st = (Student) userRepo.getUserById(studentId);
            st.getCourses().remove(course);
           courseRepo.updateCourse(course);
            return "Student UnEnrolled successfully.";
        } else {
            return "Student is not enrolled in this course.";
        }
    }
//=============================================================

    public User updateStudent(Student u) {
        Optional<User> userToUpdate = userRepo.findUserViaEmail(u.getEmail());

        if (userToUpdate.isPresent()) {
            System.out.println("here");
            Student existingUser = (Student) userToUpdate.get();

            // Update GPA if it's within the valid range
            if (u.getGPA() >= 0.0 && u.getGPA() <= 4.0) {
                existingUser.setGPA(u.getGPA());
            }

            // Update name if it's not null
            if (u.getName() != null) {
                existingUser.setName(u.getName());
            }

            if (u.getPassword() != null) {
                existingUser.setPassword(u.getPassword());
            }

            if (existingUser instanceof Student) {
                Student student = (Student) existingUser;

                if (u.getMajor() != null) {
                    student.setMajor(u.getMajor());
                }

                if (u.getGraduationYear() > 0) {
                    student.setGraduationYear(u.getGraduationYear());
                }

                if (u.getCourses() != null && !u.getCourses().isEmpty()) {
                    student.setCourses(u.getCourses());
                }
            }

            return userRepo.updateUser(existingUser);
        } else {
            // Return null or throw exception if user is not found
            return null;
        }
    }


}
