package com.example.LMS.service;

import com.example.LMS.model.User;
import com.example.LMS.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepo userRepo;

    // Register a new user
    public User register(User user) {
        Optional<User> existingUser = userRepo.findUserViaEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }
        return userRepo.saveUser(user);
    }

    // Authenticate user by email and password
    public Optional<User> login(String email, String password) {
        return userRepo.findUserViaEmail(email).filter(user -> user.getPassword().equals(password));
    }
}
