package com.example.LMS.repository;

import com.example.LMS.model.Course;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CourseRepo {
    private final Map<String, Course> courses = new HashMap<>();

    public Course save(Course course) {
        courses.put(course.getId(), course);
        return course;
    }

    public Optional<Course> findById(String id) {
        return Optional.ofNullable(courses.get(id));
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }
}

