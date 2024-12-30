package com.example.LMS.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.LMS.model.Assesment;
import com.example.LMS.model.Lesson;
import org.springframework.stereotype.Repository;

import com.example.LMS.model.Assesment;
import com.example.LMS.model.Course;

@Repository
public class CourseRepo {
    private final Map<Long, Course> courses = new HashMap<>();
    private final Map<Long, List<Lesson>> lessons = new HashMap<>();
    
    public Course save(Course course) {
        courses.put(course.getId(), course);
        System.out.println("course saved");
        return course;
    }

    public Optional<Course> findById(Long id) {
        System.out.println("hhh");
        if(courses.containsKey(id))System.out.println("found");
        return Optional.ofNullable(courses.get(id));
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }

    public void deleteById(Long id) {
        courses.remove(id);
    }

    public void updateCourse(Course course) {
        courses.put(course.getId(), course);
    }

    public Lesson addLesson(Long courseId, Lesson lesson) {
        List<Lesson> lessonList = lessons.getOrDefault(lesson.getCourseId(), new ArrayList<>());
        lessonList.add(lesson);
        Course course = courses.get(lesson.getCourseId());
        course.Lessons.add(lesson);
        lessons.put(lesson.getCourseId(), lessonList);
        return lesson;
    }
    public void addAssigment(Long courseId,Assesment assesment){

        this.findById(courseId).get().addAssigment(assesment);
    }
    public List<Assesment> viewAssesments(Long courseId){

        return this.findById(courseId).get().getAssignments();
    }

    public Course addAssessment(Assesment a){

        courses.get(a.getCourseID()).setAssesments(a);

        return courses.get(a.getCourseID());

    }
    
    public  String view_course( long CourseID) {
        // Check if the course exists in the courses map
        Course course = courses.get(CourseID);
        if (course == null) {
            return "Course not found.";
        }
        else{
            return course.getDescription();
        }
    }

}
