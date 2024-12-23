package com.example.LMS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LMS.model.User;
import com.example.LMS.repository.UserRepo;

@Service
public class UserService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    //
    // Constructor injection
    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user
    public User register(User  user) {
        Optional<User> existingUser = userRepo.findUserViaEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }

        // Encode the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.saveUser(user);
    }

    // Authenticate user by email and password
    public Optional<User> login(String email, String password) {
        return userRepo.findUserViaEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword())); // Compare encoded password
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findUserViaEmail(email); // Delegate to UserRepo
    }

    public List<User> getAllUsers() {
        return userRepo.findAllUsers();
    }
    public boolean deleteUser(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean UpdateUser(Long id, User user) {

        Optional<User> checkuser = userRepo.findById(id);

        if (checkuser.isPresent()) {
             User existingUser = checkuser.get();


            if (user.getName() != null) {
                existingUser.setName(user.getName());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getEmail()!= null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getRole()!= null) {
                existingUser.setRole(user.getRole());
            }
            if(user.getNotififcations()!=null)
               existingUser.setNotififcations(user.getNotififcations());

            return true;
        }

        return false;
    }

    public User update(User u){
        Optional<User> updatedUser =  userRepo.findUserViaEmail(u.getEmail());
        if(!updatedUser.isPresent()){
            throw new IllegalArgumentException("There is no such user with given email");

        }
        return userRepo.updateUser(u);

    }

     // View user role
     public String viewUserRole(Long userId) {
        return userRepo.findById(userId)
                .map(User::getRole)
                .orElse("User not found");
    }

}
