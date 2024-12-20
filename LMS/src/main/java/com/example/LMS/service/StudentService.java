package com.example.LMS.service;

import com.example.LMS.model.Course;
import com.example.LMS.model.Student;
import com.example.LMS.model.User;
import com.example.LMS.repository.StudentRepo;
import com.example.LMS.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private   UserService userService;
    private   StudentRepo studentRepo;
    private final UserRepo userRepo;


    @Autowired
    public StudentService(UserRepo userRepo, UserService userService,StudentRepo studentRepo) {
        this.userRepo = userRepo;
        this.studentRepo=studentRepo;
        this.userService=userService;
    }

    //=============================================================
    public Optional<User> register(User user) {
        assert userRepo != null;
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }

        // Save the user and return it
        User savedUser = userRepo.saveUser(user);

        // Ensure the saved user is of the expected type
        if (savedUser instanceof Student) {
            return Optional.of(savedUser);
        }
        return Optional.empty(); // Or return the user as-is if type checking isn't necessary
    }
    //=============================================================
    public Optional<Student>login(String email,String pass){
        return userService.login(email,pass).filter(Student.class::isInstance).map(Student.class::cast);
    }
    //=============================================================
    public Optional<Student>FindStudentBYEmail(String email){
        return userService.findByEmail(email).filter(Student.class::isInstance).map(Student.class::cast);
    }
    //=============================================================
    public List<Student> GetAllStudents(){
        return studentRepo.findAllStudents();
    }
    //=============================================================
    public String EnrollInCourse(String studentId,long courseId){
        assert studentRepo != null;
        return studentRepo.EnrollInCourse(studentId,courseId);
    }
    //=============================================================
    public String UnEnrollFromCourse(String courseId, long studentId) {
        return studentRepo.UnEnrollFromCourse(courseId,studentId);
    }
//=============================================================


}
