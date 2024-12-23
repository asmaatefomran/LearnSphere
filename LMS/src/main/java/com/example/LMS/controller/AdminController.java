package com.example.LMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.LMS.model.Admin;
import com.example.LMS.model.User;
import com.example.LMS.service.AdminService;
import com.example.LMS.service.UserService;

@RestController
@RequestMapping("api/admin")
public class AdminController extends UserController<Admin> {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;


    // Endpoint to get all admin users
    @GetMapping("/admins")
    public ResponseEntity<List<User>> getAllAdmins() {
        List<User> admins = userService.getAllUsers().stream()
                .filter(user -> "Admin".equalsIgnoreCase(user.getRole()))
                .toList();

        return ResponseEntity.ok(admins);
    }

    // Endpoint to view user roles
    @GetMapping("/view-user-roles")
    public ResponseEntity<String> viewUserRoles(@RequestParam String email) {
        String role = adminService.viewUserRoles(email);
        return ResponseEntity.ok("User Role: " + role);
    }

    // Endpoint to manage enrolled students in a course
    @PostMapping("/manage-enrolled-students")
    public ResponseEntity<String> manageEnrolledStudents(@RequestParam Long courseId, @RequestBody List<String> students) {
        adminService.manageEnrolledStudents(courseId, students);
        return ResponseEntity.ok("Enrolled students updated successfully.");
    }

    // Endpoint to view enrolled students in a course
    @GetMapping("/view-enrolled-students")
    public ResponseEntity<List<String>> viewEnrolledStudents(@RequestParam Long courseId) {
        List<String> students = adminService.viewEnrolledStudents(courseId);
        return ResponseEntity.ok(students);
    }

    // Endpoint to generate preformed charts
    @GetMapping("/generate-charts")
    public ResponseEntity<String> generatePreformedCharts() {
        String message = adminService.generatePreformedCharts();
        return ResponseEntity.ok(message);
    }

    // Endpoint to manage admin profile
    @PutMapping("/manage-profile")
    public ResponseEntity<String> manageAdminProfile(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {
        adminService.manageAdminProfile(name, email, password);
        return ResponseEntity.ok("Admin profile updated successfully.");
    }
}
