package com.example.LMS.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.LMS.model.User;
import com.example.LMS.repository.UserRepo;

class UserServiceTest {

    @Mock
    private UserRepo userRepository; // Mock the UserRepo

    @Mock
    private PasswordEncoder passwordEncoder; // Mock the PasswordEncoder

    @InjectMocks
    private UserService userService; // Inject the mocks into UserService

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testRegister() {
        User user = new User("Alice", "alice@example.com", "password123", "STUDENT");

        // Mock behavior of findUserViaEmail and saveUser
        when(userRepository.findUserViaEmail("alice@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.saveUser(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setId(1L); // Mock an ID for the saved user
            return savedUser;
        });

        // Register the user
        User registeredUser = userService.register(user);

        assertNotNull(registeredUser.getId()); // Assert that the user has an ID
        assertEquals("encodedPassword", registeredUser.getPassword()); // Assert the password was encoded
        verify(userRepository, times(1)).saveUser(any(User.class)); // Verify saveUser was called once
        verify(passwordEncoder, times(1)).encode("password123"); // Verify the password was encoded
    }

    @Test
    void testDuplicateEmail() {
        User user1 = new User("Alice", "alice@example.com", "password123", "STUDENT");
        when(userRepository.findUserViaEmail("alice@example.com")).thenReturn(Optional.of(user1));

        User user2 = new User("Bob", "alice@example.com", "password456", "INSTRUCTOR");

        assertThrows(IllegalArgumentException.class, () -> userService.register(user2));
        verify(userRepository, never()).saveUser(any(User.class)); // Ensure saveUser is never called
    }

    @Test
    void testLoginSuccess() {
        User user = new User("Alice", "alice@example.com", "encodedPassword", "STUDENT");

        // Mock behavior of findUserViaEmail
        when(userRepository.findUserViaEmail("alice@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "encodedPassword")).thenReturn(true);

        Optional<User> loginUser = userService.login("alice@example.com", "password123");
        assertTrue(loginUser.isPresent());
        verify(passwordEncoder, times(1)).matches("password123", "encodedPassword"); // Verify password check
    }

    @Test
    void testLoginFailure() {
        User user = new User("Alice", "alice@example.com", "encodedPassword", "STUDENT");

        // Mock behavior of findUserViaEmail
        when(userRepository.findUserViaEmail("alice@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongpassword", "encodedPassword")).thenReturn(false);

        Optional<User> loginUser = userService.login("alice@example.com", "wrongpassword");
        assertFalse(loginUser.isPresent());
        verify(passwordEncoder, times(1)).matches("wrongpassword", "encodedPassword"); // Verify password check
    }
    void testDeleteUserWhenFound() {
  
    User user = new User("Nada","Nada@gmail.com","Nada123","Student");
    String email= user.getEmail();
    Long Id =user.getId();
   
    boolean isDeleted = userService.deleteUser(Id);

 
    assertTrue(isDeleted);
    //must not found after delete
    assertFalse(userService.findById(Id).isPresent());
}

@Test
void testDeleteuserWhenNotFound() {
    
    Long Id = 999L; // user not found 
    boolean isDeleted = userService.deleteUser(Id);
    assertFalse(isDeleted);
}

@Test
void testUpdateUserWhenFound() {
    
    User User = new User("Noura", "Noura@gmail.com", "Noura123", "admin");
    User.setId(1L); 
    
    
    when(userRepository.findById(1L)).thenReturn(Optional.of(User));

    User updatedUser = new User("Noura", "Noura@gmail.com", "Noura123", "admin");


    when(userRepository.saveUser(any(User.class))).thenReturn(updatedUser);

    boolean isUpdated = userService.UpdateUser(1L, updatedUser);
    assertTrue(isUpdated);
    
  
    User retrievedUser = userService.findById(1L).orElse(null);
    assertNotNull(retrievedUser);
    assertEquals("Noura", retrievedUser.getName());
    assertEquals("Noura@gmail.com", retrievedUser.getEmail());
    assertEquals("admin", retrievedUser.getRole());
}

@Test
void testUpdateCourseWhenNotFound() {
   
    Long Id = 999L; 

    User user = new User("Nada","Nada@gmail.com","Nada123","Student");;
    boolean isUpdated = userService.UpdateUser(Id, user);
    assertFalse(isUpdated);
}
}
