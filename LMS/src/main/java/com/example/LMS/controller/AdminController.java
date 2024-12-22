package com.example.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LMS.model.Admin;
import com.example.LMS.model.User;
import com.example.LMS.service.UserService;
@RestController

@RequestMapping("api/admin")
public class AdminController extends UserController<Admin>{
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> Admins = userService.getAllUsers().stream()
                .filter(user -> "Admin".equalsIgnoreCase(user.getRole()))
                .toList(); // Collect only students

        return ResponseEntity.ok(Admins);
    }
}
