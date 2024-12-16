package com.example.LMS.repository;

import com.example.LMS.model.User;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class UserRepo {
    private final Map<String, User> users = new HashMap<>();

    // Save a new user
    public User saveUser(User user) {
        users.put(user.getId(), user);
        return user;
    }

    // Find user by email
    public Optional<User> findUserViaEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // Retrieve all users (for testing or admin view)
    public List<User> findAllUsers() {
        return new ArrayList<>(users.values());
    }
}
