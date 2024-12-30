package com.example.LMS.service;

import com.example.LMS.model.Assesment;
import com.example.LMS.model.Course;
import com.example.LMS.model.Question;
import com.example.LMS.model.Quiz;
import com.example.LMS.repository.CourseRepo;
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
    @Autowired
    private CourseRepo courseRepo;

    public Quiz createQuiz(Quiz q) {
        return qr.save(q);
    }

    public Quiz createQuiz(Long courseid) {

        Quiz q = new Quiz(courseRepo.findById(courseid).get().getRandomQuestions(), courseid);
        return qr.save(q);
    }

    public String uploadAssessment(Long assessID, Long StudentID,long courseID, String ans) {
        Assesment assessment ;
        Optional<Course> c = courseRepo.findById(courseID);
        if(c.isPresent()) {
            Course c2 = c.get();
            assessment = c2.getAssesment(assessID);

            if (assessment == null) {
                throw new IllegalArgumentException("Assessment with ID " + assessment.getId() + " not found.");

            }
            assessment.addSubmission(StudentID, ans);
        }
        return ("Submission uploaded for Student ID: " + StudentID);

    }


    public String getQuizGrades(Long id) {
        if (qr.findById(id).isPresent()) {
            Quiz q = qr.findById(id).get();
            return q.getStudents().toString();
        }

        return new String("there is no quiz with that id: " + id);

    }
    public String getquizes(){
        return qr.getQuizes().toString();
    }
}
