package com.example.LMS.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.LMS.model.User;
import com.example.LMS.service.UserService;

@Configuration
public class InstructorConfig {
    @Bean(name = "instructorCommandLineRunner")
    CommandLineRunner commandLineRunner(UserService repository) {
        return args -> {
            User yara = new User("yara","yara.a.@gmail.com","123456","instructor");
            User salma = new User("salma","salma.a.@gmail.com","123456","instructor");
            User zefta = new User("zefta","zefta.zefta.@gmail.com","123456","instructor");
            User koky = new User("koky","koky.a.@gmail.com","123456","instructor");
            User loly = new User("loly","loly.a.@gmail.com","123456","instructor");
            repository.register(yara);
            repository.register(salma);
            repository.register(zefta);
            repository.register(koky);
            repository.register(loly);
        };
    }

}