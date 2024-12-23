package com.example.LMS.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.LMS.model.Assesment;
import com.example.LMS.model.Course;
@Repository
public class AssigmentRepo {

    CourseRepo Courserepo;
    public Assesment add(Assesment ass,Long courseId){
        Optional<Course> course= Courserepo.findById(courseId);
        if(course.isPresent()){
            Courserepo.addAssigment(courseId, ass);
        }
        return ass;
    }

}
