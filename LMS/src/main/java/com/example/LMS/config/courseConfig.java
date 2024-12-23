package com.example.LMS.config;

import com.example.LMS.service.CourseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class courseConfig {
    @Bean(name = "CourseCommandLineRunner"  )
    CommandLineRunner commandLineRunner(CourseService repository) {
        return args -> {
            repository.createCourse("CS","Course for first grade", Long.valueOf("6"));
            repository.createCourse("DB"," Course for Second grade", Long.valueOf("6"));
            repository.createCourse(    "Algo"," Course for Second grade", Long.valueOf("6"));
            repository.createCourse("Arch"," Course for Third grade", Long.valueOf("6"));
        };
    }

}

