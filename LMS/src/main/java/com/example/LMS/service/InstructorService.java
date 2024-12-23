package com.example.LMS.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.LMS.model.Student;
import com.example.LMS.model.User;
import com.example.LMS.repository.StudentRepo;
import com.example.LMS.repository.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InstructorService {
    private UserService userService;
    private final UserRepo userRepo;

}
