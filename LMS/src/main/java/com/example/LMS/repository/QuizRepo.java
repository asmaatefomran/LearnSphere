package com.example.LMS.repository;

import com.example.LMS.model.Course;
import com.example.LMS.model.Question;
import com.example.LMS.model.Quiz;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class QuizRepo {
    private final Map<Long, Quiz> quizes = new HashMap<>();

    public Quiz save(Quiz q) {
        quizes.put(q.setId(), q);
        return q;
    }

    public Optional<Quiz> findById(Long id) {
        return Optional.ofNullable(quizes.get(id));
    }

    public List<Quiz> findAll() {
        return new ArrayList<>(quizes.values());
    }

    public void deleteById(Long id) {
        quizes.remove(id);
    }

    public void updateCourse(Quiz q) {
        quizes.put(q.setId(), q);
    }

    // public String gradeQuiz(Long id) {
    //     List<Question> list= this.findById(id).get().getQuestions();
    
    //     return new String();
    // }
}
