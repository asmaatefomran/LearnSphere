package com.example.LMS.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.LMS.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.service.CourseService;
import com.example.LMS.service.InstructorService;
import com.example.LMS.service.QuizService;
// import com.example.LMS.service.InstructorService;
import com.example.LMS.service.UserService;

import ch.qos.logback.core.joran.sanity.Pair;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private InstructorService instructorService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody Instructor instructor) {
        User registeredUser = userService.register(instructor);
        if (registeredUser != null) {
            return ResponseEntity.ok(registeredUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllInstructor() {
        // Filter users with the role "instructor"
        List<User> Instructor = userService.getAllUsers().stream()
                .filter(user -> "instructor".equalsIgnoreCase(user.getRole()))
                .toList(); // Collect only instructor

        return ResponseEntity.ok(Instructor);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        boolean isUpdated = userService.UpdateUser(id, user);

        if (isUpdated) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/removeStudent")
    public ResponseEntity<String> removeStudent(@RequestParam Long studId, @RequestParam Long courseId,
            @RequestParam Long InstId) {
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isPresent()) {
            Long id = Long.parseLong(course.get().getInstructorId());
            if (id.equals(InstId)) {
                if (userService.deleteUser(studId))
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body("student with that id " + studId + " is deleted");

                ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("there is no student with that id in that course");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("you don't have the right to remove student from that course");

        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("there is no course with that id");

    }

    @PostMapping("/createAssignment")
    public ResponseEntity<String> createAssignment(@RequestParam Long courseId, @RequestParam Long InstId,
            @RequestBody Assignment assesment) {
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isPresent()) {
            Long id = Long.parseLong(course.get().getInstructorId());
            if (id.equals(InstId)) {
                assesment.setCourseID(courseId);
                assesment.setInstructorId(InstId);
                assesment.setId();

                courseService.addAssigment(courseId, assesment);
                return ResponseEntity.status(HttpStatus.CREATED).body(assesment.toString());
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("you don't have the right to add assigment to that course");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("there is no course with that id");
        }
    }

    @GetMapping("/ViewAssignment")
    public ResponseEntity<String> ViewAssignment(@RequestParam Long courseId) {
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.viewAssesments(courseId).toString());

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("there is no course with that id");
        }

    }

    @PostMapping("/gradeAssignment")
    public ResponseEntity<String> gradeAssignment(@RequestParam Long courseId, @RequestParam Long assigId,
            @RequestBody List<HashMap<Long, StudentGradeFeedback>> students) {
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isPresent()) {
            Assesment assesment = course.get().getAssesment(assigId);
            assesment.setStudents(students);

            return ResponseEntity.status(HttpStatus.CREATED).body(students.toString());

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("there is no course with that id");
        }

    }

    @PostMapping("/createQuiz")
    public ResponseEntity<String> createQuiz(@RequestBody Quiz q, @RequestParam Long courseId,
            @RequestParam Long InstId) {
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isPresent()) {
            Long id = Long.parseLong(course.get().getInstructorId());

            if (id.equals(InstId)) {
                Quiz createdQuiz = quizService.createQuiz(q);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz.toString());
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("you don't have the right to add assigment to that course");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("there is no course with that id");
        }

    }

    @PostMapping("/createQuizRandom")
    public ResponseEntity<String> createQuizRandom( @RequestParam Long courseId,
            @RequestParam Long InstId) {
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isPresent()) {
            Long id = Long.parseLong(course.get().getInstructorId());
            if (id.equals(InstId)) {
                Quiz createdQuiz = quizService.createQuiz(courseId);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz.toString());
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("you don't have the right to add assigment to that course");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("there is no course with that id");
        }

    }
    

    @GetMapping("/viewEnrollStud")
    public ResponseEntity<String> viewEnrolledStudents(@RequestParam String instructorId, @RequestParam Long couid) {
        try {
            List<User> enrolled = instructorService.viewStudentInCourse(instructorId, couid);
            return ResponseEntity.ok(enrolled.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("al dnia msh tmm");
        }

    }

    @GetMapping("/viewNotification")
    public ResponseEntity<String> viewNotification(@RequestParam Long userId) {
        try {
            User user = userService.getUserbyId(userId);

            if (user == null) {
                return ResponseEntity.badRequest().body("There is no user by this id ");
            }
            List<Notification> notifications = user.getNotifications();
            return ResponseEntity.ok(notifications.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("al dnia msh tmm");
        }

    }


    @GetMapping("/getQuizGrades")
    public String getQuizgrades(@RequestParam Long quizId) {
        return quizService.getQuizGrades(quizId);
    }

    @GetMapping("/getQuizes")
    public String getQuizes() {
        return quizService.getquizes();
    }
}
