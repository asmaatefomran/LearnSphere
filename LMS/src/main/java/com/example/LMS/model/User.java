package com.example.LMS.model;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Setter
@Getter
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    List<Notification> notififcations;

    public User(String name, String email, String password, String role) {
        this.id = UserIdGenerotor.generateId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Custom setter for id
    public long setId() {
        this.id = UserIdGenerotor.generateId();
        return this.id;
    }

    // Default constructor for frameworks like JPA or for testing
    public User() {}
}
