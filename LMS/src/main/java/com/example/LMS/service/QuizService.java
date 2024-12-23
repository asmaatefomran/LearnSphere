package com.example.LMS.service;

import com.example.LMS.model.Question;
import com.example.LMS.model.Quiz;
import com.example.LMS.repository.QuizRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Getter
@Service
public class QuizService {
    @Autowired
    private QuizRepo qr;

    public Quiz createQuiz(Quiz q) {
        return qr.save(q);
    }

    // public String GradeQuiz(Long id){
    // if( qr.findById(id).isPresent())
    // {
    // Quiz q = qr.findById(id).get();

    // }

    // return new String("there is no quiz with that id: "+id);
    // }

}
