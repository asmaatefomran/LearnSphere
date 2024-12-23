package com.example.LMS.repository;

import com.example.LMS.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AdminRepo {

    private final Map<Long, User> users = new HashMap<>();
    private final Map<Long, List<String>> courseEnrollments = new HashMap<>();

    public List<String> getAllUserRoles() {
        return users.values().stream()
                .map(user -> "User ID: " + user.getId() + ", Role: " + user.getRole())
                .collect(Collectors.toList());
    }

    public List<String> getEnrolledStudents(Long courseId) {
        return courseEnrollments.getOrDefault(courseId, Collections.emptyList());
    }


    public boolean updateAdminProfile(Long adminId, String name, String email, String password) {
        User admin = users.get(adminId);
        if (admin != null && "Admin".equalsIgnoreCase(admin.getRole())) {
            if (name != null) admin.setName(name);
            if (email != null) admin.setEmail(email);
            if (password != null) admin.setPassword(password);
            return true;
        }
        return false;
    }


    public Map<String, Integer> generateChartData() {
        Map<String, Integer> roleCounts = new HashMap<>();
        users.values().forEach(user -> {
            roleCounts.put(user.getRole(), roleCounts.getOrDefault(user.getRole(), 0) + 1);
        });
        return roleCounts;
    }

    public void saveUser(User user) {
        users.put(user.getId(), user);
    }
}
