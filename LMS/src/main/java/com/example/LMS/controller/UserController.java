package com.example.LMS.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.model.User;
import com.example.LMS.service.UserService;

@RestController

@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint for registering a new user
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            User registeredUser = userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    // Endpoint to retrieve the account details
    @GetMapping("/account")
    public ResponseEntity<User> getAccount(@RequestParam String email) {
        // Call the service method to find user by email
        Optional<User> user = userService.findByEmail(email);

        // Return the user if found, otherwise return a 404 NOT FOUND
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateCourse(@PathVariable Long id,@RequestBody User user) {
        boolean isUpdated = userService.UpdateUser(id, user);

        if (isUpdated) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
