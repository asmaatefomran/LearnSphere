package com.example.LMS.repository;
import com.example.LMS.model.Assesment;
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

//     private final Map<Long, Assesment> Assessments = new HashMap<>(); //malak
//     public Quiz save( Quiz q){
//         Assessments.put(q.setId(),q);
//         return q;
//     }
//     public Assesment findById(Long id){ //malak
//         if(Assessments.containsKey(id))
//         return Assessments.get(id);
//         return null;
//     }
//     public List<Assesment> findAll() { //malak
//         return new ArrayList<>(Assessments.values());

//     }

    // public void deleteById(Long id) { //malak
    //     Assessments.remove(id);
    // }

    public void updateCourse(Quiz q) {
        quizes.put(q.setId(), q);

//         Assessments.put(q.getId(), q); //malak add that here
    }

   // public String gradeQuiz(Long id) { //salma
    //     List<Question> list= this.findById(id).get().getQuestions();
    
    //     return new String();
    // }
      
//     public void uplaodAssesment(Long AssessmenID,Long StudentId,String ans){  //malak
//         Assessments.get(AssessmenID).addSubmission(StudentId,ans);
//     }


}
