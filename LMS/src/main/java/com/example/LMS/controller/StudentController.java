package com.example.LMS.controller;

import com.example.LMS.model.Notification;
import com.example.LMS.model.Student;
import com.example.LMS.model.User;
import com.example.LMS.model.lesson;
import com.example.LMS.service.NotificationService;
import com.example.LMS.service.StudentService;
import com.example.LMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class
StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/register")
    public ResponseEntity<User> registerStudent(@RequestBody Student student) {
        User registeredUser = userService.register(student);
        if (registeredUser != null) {
            notificationService.AddNotification("You have registered Successfully",registeredUser.getId());
            return ResponseEntity.ok(registeredUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginStudent(@RequestParam String email, @RequestParam String password) {
        // Call the login method in UserService
        User loggedInUser = userService.login(email, password).orElse(null);

        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> enroll(@RequestParam Long StudId, @RequestParam long Couid) {

        // Call the enrollInCourse method from the StudentService
        String result = studentService.EnrollInCourse(StudId, Couid);
        notificationService.AddNotification("You have enrolled the {Couid} Successfully",StudId);

        // Return the response
        return ResponseEntity.ok(result);
    }

    @PostMapping("/Unenroll")
    public ResponseEntity<String> Unenroll(@RequestParam Long StudId, @RequestParam Long Couid) {
        // Convert Long IDs to String if required by the service method
        String studentId = StudId.toString();
        long courseId = Couid;

        // Call the enrollInCourse method from the StudentService
        String result = studentService.UnEnrollFromCourse(Long.valueOf(studentId),courseId);
        notificationService.AddNotification("You have Unenrolled the {Couid} Successfully",StudId);

        // Return the response
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllStudents() {
        // Filter users with the role "student"
        List<User> students = userService.getAllUsers().stream()
                .filter(user -> "student".equalsIgnoreCase(user.getRole()))
                .toList(); // Collect only students

        return ResponseEntity.ok(students);
    }

    @PostMapping("/update")
    public ResponseEntity<Optional<User>> updateStudent(@RequestBody Student stu){
    Optional<User> updatedUser = studentService.updateStudent(stu);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    // not tested yet
    @GetMapping("/attendlesson?id={id}&&name={name}&&couid={couid}")
    public ResponseEntity<Optional<lesson>> attendLesson(@PathVariable long id, @PathVariable String name, @PathVariable Long Couid){
        Optional<lesson> l = studentService.attendlesson(id,name,Couid);
        return ResponseEntity.ok().body(l);
    }

    @GetMapping ("notifications")
    public ResponseEntity<Optional<List<Notification>>> viewNotifications(@RequestParam Long RecID){
   ;
        Optional<List<Notification>> notificationList = notificationService.ShowNotifactions(RecID);
        return ResponseEntity.ok().body(notificationList);
    }

}
