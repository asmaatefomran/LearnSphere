package com.example.LMS.repository;

import com.example.LMS.controller.QuizController;
import com.example.LMS.model.Assesment;
import com.example.LMS.model.Course;
import com.example.LMS.model.Quiz;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class QuizRepo {
    private final Map<Long, Assesment> Assessments = new HashMap<>();
    public Quiz save( Quiz q){
        Assessments.put(q.setId(),q);
        return q;
    }
    public Assesment findById(Long id){
        if(Assessments.containsKey(id))
        return Assessments.get(id);
        return null;
    }
    public List<Assesment> findAll() {
        return new ArrayList<>(Assessments.values());
    }
    public void deleteById(Long id) {
        Assessments.remove(id);
    }

    public void updateCourse(Quiz q) {
        Assessments.put(q.getId(), q);
    }


    public void uplaodAssesment(Long AssessmenID,Long StudentId,String ans){
        Assessments.get(AssessmenID).addSubmission(StudentId,ans);
    }



}
