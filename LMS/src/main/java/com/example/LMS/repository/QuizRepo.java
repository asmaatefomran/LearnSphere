package com.example.LMS.repository;

import com.example.LMS.controller.QuizController;
import com.example.LMS.model.*;

import lombok.Getter;

import org.springframework.stereotype.Repository;

import java.util.*;
@Getter
@Repository
public class QuizRepo {

    private final Map<Long, Quiz> quizes = new HashMap<>();

    private final Map<Long, Assesment> Assessments = new HashMap<>(); // malak

    public Quiz save(Quiz q) {
        quizes.put(q.getId(), q);
        return q;
    }

    public Optional<Quiz> findById(Long id) {
        return Optional.ofNullable(quizes.get(id));
    }

    public List<Quiz> findAll() {
        return new ArrayList<>(quizes.values());
    }

    public Assesment save(Assignment q) {
        Assessments.put(q.setId(), q);
        return q;
    }

    public Assesment findAById(Long id) { // malak
        if (Assessments.containsKey(id))
            return Assessments.get(id);
        return null;
    }

    public List<Assesment> findAllA() { // malak
        return new ArrayList<>(Assessments.values());

    }

    public void deleteById(Long id) {
        Assessments.remove(id);
    }


    public void uplaodAssesment(Long AssessmenID, Long StudentId, String ans) { // malak
        Assessments.get(AssessmenID).addSubmission(StudentId, ans);
    }

    public Quiz TakeQuiz(Long StudentId, Long quizID) {
        quizes.get(quizID).TakeQuiz(StudentId);
        return quizes.get(quizID);
    }

    public Integer submitQuiz(Long StudentId,Long quizId){
        return quizes.get(quizId).gradeQuiz(StudentId);
    }

}
