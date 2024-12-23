package com.example.LMS.repository;


import com.example.LMS.controller.QuizController;
import com.example.LMS.model.*;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class QuizRepo {

    private final Map<Long, Quiz> quizes = new HashMap<>();
    
     private final Map<Long, Assesment> Assessments = new HashMap<>(); //malak

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
     public Assesment save(Assignment q){
         Assessments.put(q.setId(),q);
         return q;
     }
     public Assesment findAById(Long id){ //malak
         if(Assessments.containsKey(id))
         return Assessments.get(id);
         return null;
     }
     public List<Assesment> findAllA() { //malak
         return new ArrayList<>(Assessments.values());

     }

    public void deleteById(Long id) {
        Assessments.remove(id);
    }


    public String gradeQuiz(Long id) { //salma
         List<Question> list= this.findById(id).get().getQuestions();

         return new String();
     }

     public void uplaodAssesment(Long AssessmenID,Long StudentId,String ans){  //malak
         Assessments.get(AssessmenID).addSubmission(StudentId,ans);
     }


}
