package com.example.LMS.model;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
    private List<Notification> notifications = new ArrayList<>(); // Initialize the notifications list


    public User(String name, String email, String password,String role) {
        this.id = UserIdGenerotor.generateId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;

    }
    public User(String name, String email, String password) {
        this.id = UserIdGenerotor.generateId();
        this.name = name;
        this.email = email;
        this.password = password;

    }


    // Custom setter for id
    public long setId() {
        this.id = UserIdGenerotor.generateId();
        return this.id;
    }

    // Default constructor for frameworks like JPA or for testing
    public User() {}
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
                + ", notififcations=" + notifications + "]";
    }
    
}
