package com.example.LMS.service;

import com.example.LMS.model.User;
import com.example.LMS.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserRepo userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepo();
        userService = new UserService();
        userService.userRepo = userRepository;
    }

    @Test
    void testRegister() {
        User user = new User("Alice", "alice@example.com", "password123", "STUDENT");
        User registeredUser = userService.register(user);
        assertNotNull(registeredUser.getId());
    }

    @Test
    void testDuplicateEmail() {
        User user1 = new User("Alice", "alice@example.com", "password123", "STUDENT");
        userService.register(user1);

        User user2 = new User("Bob", "alice@example.com", "password456", "INSTRUCTOR");
        assertThrows(IllegalArgumentException.class, () -> userService.register(user2));
    }

    @Test
    void testLoginSuccess() {
        User user = new User("Alice", "alice@example.com", "password123", "STUDENT");
        userService.register(user);

        Optional<User> loginUser = userService.login("alice@example.com", "password123");
        assertTrue(loginUser.isPresent());
    }

    @Test
    void testLoginFailure() {
        User user = new User("Alice", "alice@example.com", "password123", "STUDENT");
        userService.register(user);

        Optional<User> loginUser = userService.login("alice@example.com", "wrongpassword");
        assertFalse(loginUser.isPresent());
    }
}
