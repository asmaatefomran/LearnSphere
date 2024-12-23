package com.example.LMS.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.LMS.model.User;
import com.example.LMS.service.UserService;

@Configuration
public class StudentConfig {
    @Bean(name = "studentCommandLineRunner")
    CommandLineRunner commandLineRunner(UserService repository) {
        return args -> {
            User yara = new User("yara","yaras.a.@gmail.com","123456","student");
            User salma = new User("salma","salmas.a.@gmail.com","123456","student");
            User zefta = new User("zefta","zeftas.zefta.@gmail.com","123456","student");
            User koky = new User("koky","kokys.a.@gmail.com","123456","student");
            User loly = new User("loly","lolys.a.@gmail.com","123456","student");
            repository.register(yara);
            repository.register(salma);
            repository.register(zefta);
            repository.register(koky);
            repository.register(loly);
        };
    }

}
