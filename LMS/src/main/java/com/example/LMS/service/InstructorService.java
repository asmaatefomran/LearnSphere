package com.example.LMS.service;

import org.springframework.stereotype.Service;
import com.example.LMS.repository.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InstructorService {
    private UserService userService;
    private final UserRepo userRepo;

}
