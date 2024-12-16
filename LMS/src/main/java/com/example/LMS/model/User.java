package com.example.LMS.model;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


@Getter
@Setter
public class User {
    private Long id; // Lombok will generate setId() and getId()
    private String name;
    private String email;
    private String password;
    private String role;

    // Constructor for creating User objects
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Default constructor for frameworks like JPA or for testing
    public User() {}
}
