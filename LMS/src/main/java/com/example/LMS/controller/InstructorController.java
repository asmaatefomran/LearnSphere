// package com.example.LMS.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.LMS.model.Instructor;
// import com.example.LMS.model.User;
// import com.example.LMS.service.InstructorService;
// import com.example.LMS.service.UserService;
// @RestController
// @RequestMapping("/api/instructor")
// public class InstructorController {
//     @Autowired
//     private InstructorService InstructorService;
//     @Autowired
//     private UserService userService;
//      @PostMapping("/register")
//     public ResponseEntity<User> registerStudent(@RequestBody  Instructor instructor) {
//         User registeredUser = userService.register(instructor);
//         if (registeredUser != null) {
//             return ResponseEntity.ok(registeredUser);
//         } else {
//             return ResponseEntity.badRequest().build();
//         }
//     }


// }
