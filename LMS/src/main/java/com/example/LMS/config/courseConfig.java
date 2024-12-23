package com.example.LMS.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.LMS.service.CourseService;
@Configuration
public class courseConfig {

    @Bean(name = "courseCommandLineRunner")
    CommandLineRunner commandLineRunner(CourseService repository) {
        return args -> {

            repository.createCourse("c1", "c1", 6L);
            repository.createCourse("c1", "c1", 16L);
            repository.createCourse("c1", "c1", 16L);
            repository.createCourse("c1", "c1", 16L);
            repository.createCourse("c1", "c1", 16L);
        };
    }
}
