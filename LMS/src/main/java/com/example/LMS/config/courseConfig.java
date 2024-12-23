package com.example.LMS.config;

import com.example.LMS.model.Course;
import com.example.LMS.service.CourseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class courseConfig {
    @Bean(name = "CourseCommandLineRunner")
    CommandLineRunner commandLineRunner(CourseService repository) {
        return args -> {
            repository.createCourse("CS","Course for first grade",6L);
            repository.createCourse("DB"," Course for Second grade",6L);
            repository.createCourse("Algo"," Course for Second grade",6L);
            repository.createCourse("Arch"," Course for Third grade",6L);
        };
    }

}

