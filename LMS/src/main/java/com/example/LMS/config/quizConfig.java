package com.example.LMS.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.example.LMS.model.Question;
import com.example.LMS.model.Quiz;
import com.example.LMS.model.User;
import com.example.LMS.service.QuizService;

public class quizConfig {
    @Bean(name = "quizCommandLineRunner")
    CommandLineRunner commandLineRunner(QuizService repository) {
        return args -> {
            List<Question> questions = Arrays.asList(
            new Question("What is the capital of France?", "Paris", 5),
            new Question("What is 2 + 2?", "4", 3),
            new Question("What is the color of the sky?", "Blue", 2)
        );
            Quiz q1= new Quiz(questions,3);
            repository.createQuiz(q1);
            // repository.createQuiz(null);
            // repository.createQuiz(null);
            // repository.createQuiz(null);            
            // repository.createQuiz(null);  
      
        };
    }
}
