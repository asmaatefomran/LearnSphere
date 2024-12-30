package com.example.LMS.controller;

import java.util.List;
import java.util.Optional;

import com.example.LMS.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.service.CourseService;
import com.example.LMS.service.NotificationService;
import com.example.LMS.service.QuizService;
import com.example.LMS.service.StudentService;
import com.example.LMS.service.UserService;

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
    @Autowired
    private QuizService quizService;
    @Autowired
    private CourseService courseService;

    @PostMapping("/register")
    public ResponseEntity<User> registerStudent(@RequestBody Student student) {
        User registeredUser = userService.register(student);
        if (registeredUser != null) {
            notificationService.AddNotification("You have registered Successfully", registeredUser.getId());
            return ResponseEntity.ok(registeredUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginStudent(@RequestParam String email, @RequestParam String password) {
        User loggedInUser = userService.login(email, password).orElse(null);
        if (loggedInUser != null) {
         notificationService.AddNotification("You have loggined Successfully", loggedInUser.getId());
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/enroll")
    public ResponseEntity<String> enroll(@RequestParam Long StudId, @RequestParam long Couid) {

        String result = studentService.EnrollInCourse(StudId, Couid);
        Optional<Course> optionalCourse = courseService.getCourseById(Couid);
        Course course = optionalCourse.get();
        long instructorId = Long.parseLong (course.getInstructorId());
        notificationService.notifyInstructorForEnrollment(instructorId, "A student has enrolled in your course: " + course.getTitle() + " and his id is  " + StudId);
        notificationService.AddNotification("You have enrolled the course that it's id: "+Couid+" and name is: "+ course.getTitle() +" Successfully",StudId);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/Unenroll")
    public ResponseEntity<String> Unenroll(@RequestParam Long StudId, @RequestParam Long Couid) {
        String studentId = StudId.toString();
        long courseId = Couid;
        String result = studentService.UnEnrollFromCourse(Long.valueOf(studentId),courseId);
        notificationService.AddNotification("You have Unenrolled the {Couid} Successfully",StudId);

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllStudents() {
       List<User> students = userService.getAllUsers().stream()
                .filter(user -> "student".equalsIgnoreCase(user.getRole()))
                .toList();

        return ResponseEntity.ok(students);
    }

    @PostMapping("/update")
    public ResponseEntity<Optional<User>> updateStudent(@RequestBody Student stu){
    Optional<User> updatedUser = studentService.updateStudent(stu);
        if (updatedUser.isPresent()) {
            User u = updatedUser.get();

            notificationService.AddNotification("Your profile has been updated",u.getId());
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/attendlesson")
    public ResponseEntity<Optional<Lesson>> attendLesson(@RequestParam long Studid, @RequestParam String Lessonname, @RequestParam Long Couid){
;
        Optional<Lesson> l = studentService.attendlesson(Studid,Lessonname,Couid);
        if(l.isPresent())
            notificationService.AddNotification("Your attendance has been recorded!",Studid);
        return ResponseEntity.ok().body(l);
    }

    @GetMapping ("notifications")
    public ResponseEntity<Optional<List<Notification>>> viewNotifications(@RequestParam Long RecID){
   ;
        Optional<List<Notification>> notificationList = notificationService.ShowNotifactions(RecID);
        return ResponseEntity.ok().body(notificationList);
    }

     @PostMapping("/uploadassign")
     public ResponseEntity<String> uploadAssignment(@RequestParam long StudentId,@RequestParam long AssessID,@RequestParam  long courseID,
                                                    @RequestParam String ans ){
         String reasult = quizService.uploadAssessment(AssessID,StudentId,courseID,ans);
         return ResponseEntity.ok(reasult);
     }

    @GetMapping("/takequiz")
    public ResponseEntity<String> takeQuiz(@RequestParam long StudentId,@RequestParam long quizID){
        return ResponseEntity.ok(quizService.getQr().getQuizByid(quizID).toString());
    }

    @PostMapping("/submitQuiz")
    public ResponseEntity<Integer> submitQuiz(@RequestParam long StudentId,@RequestParam long quizID,@RequestBody List<String>answers) {
        System.out.println(quizService.getQr().submitQuiz(StudentId, quizID, answers));
        return ResponseEntity.ok(1);
    }
    

    @GetMapping("/view course")
    public ResponseEntity<String> view_course(@RequestParam long CourseID){
        String result=courseService.view_course(CourseID);
        return ResponseEntity.ok(result);
    }
    
}
