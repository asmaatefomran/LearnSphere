package com.example.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.model.Instructor;
import com.example.LMS.model.User;
// import com.example.LMS.service.InstructorService;
import com.example.LMS.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
    // @Autowired
    // private InstructorService InstructorService;
    @Autowired
    private UserService userService;

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
    

}
