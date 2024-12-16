package com.example.LMS.repository;

import com.example.LMS.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepo {
    private final Map<Long, User> users = new HashMap<>(); // Use id as the key (Long)

    // Save a new user
    public User saveUser(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        users.put(user.getId(), user); // Store user using their id as the key
        return user;
    }

    // Find user by email
    public Optional<User> findUserViaEmail(String email) {
        return users.values().stream()
                    .filter(user -> user.getEmail().equalsIgnoreCase(email))
                    .findFirst(); // Find the first matching user
    }
    
    
    // Retrieve all users (for testing or admin view)
    public List<User> findAllUsers() {
        return new ArrayList<>(users.values());
    }

}
