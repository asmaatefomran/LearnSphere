package com.example.LMS.service;

import com.example.LMS.model.Course;
import com.example.LMS.model.Lesson;
import com.example.LMS.model.Student;
import com.example.LMS.model.User;
import com.example.LMS.repository.StudentRepo;
import com.example.LMS.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private   UserService userService;
    private   StudentRepo studentRepo;
    private final UserRepo userRepo;
    private CourseService courseService;


    //=============================================================
    public Optional<User> register(Student user) {
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
    public String EnrollInCourse(Long studentId,long courseId){
        assert studentRepo != null;
        return studentRepo.EnrollInCourse(String.valueOf(studentId),courseId);
    }
    //=============================================================
    public String UnEnrollFromCourse(Long courseId, long studentId) {
        return studentRepo.UnEnrollFromCourse(courseId,studentId);
    }
    public Optional<Lesson> FindLessonByName(String name, Long courseId) {
            System.out.println("course found");
        Optional<Course> courseOptional = courseService.getCourseById(courseId);

        // If the course exists, search for the Lesson by name
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();

            // Assuming the `Lessons` list is populated for the course
            return course.getLessons()
                    .stream()
                    .filter(lesson -> lesson.getName().equalsIgnoreCase(name))
                    .findFirst();
        }

        // Return an empty Optional if the course doesn't exist or the Lesson isn't found
        return Optional.empty();
    }

    //=============================================================
 public Optional<User> updateStudent(Student stu){
        User i = studentRepo.updateStudent(stu);
        return Optional.of(i);
 }

     public Optional<Lesson> attendlesson(long studentId, String lessonName, long courseId) {
        Optional<Lesson> lesson1 = FindLessonByName(lessonName, courseId);

        if (lesson1.isPresent()) {
            Lesson lesson = lesson1.get();

            Optional<User> studentOptional = Optional.ofNullable(userRepo.getUserById(studentId)).stream().findFirst();
            if (studentOptional.isPresent()) {
                Student student =(Student) studentOptional.get();

                // Check if the student is already in the attendees list
               if (!lesson.getAttendees().contains(student)) {
                    // Add the student to the attendees list
                    lesson.getAttendees().add( student);
                    return Optional.of(lesson);
               } else {
                    throw new IllegalStateException("Student is already attending this Lesson.");
                }
            } else {
                throw new IllegalArgumentException("Student with ID " + studentId + " not found.");
            }
        } else {
            throw new IllegalArgumentException("Lesson " + lessonName + " not found in course ID " + courseId + ".");
        }
    }




}
