package com.example.LMS.model;

import com.example.LMS.model.StudentGradeFeedback;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Assesment {
    Long id;
    HashMap<Long, StudentGradeFeedback> students; // student ID, grade, and feedback
    List<Question> questions;
    int grade;
    Long instructorId;
    Long CourseID;

    HashMap<Long, String> Submissions = new HashMap<>();

    public long setId() {
        this.id = UserIdGenerotor.generateId();
        return this.id;
    }

    @Override
    public String toString() {
        return "Assesment [id=" + id + ", students=" + students + ", questions=" + questions + ", grade=" + grade
                + ", instructorId=" + instructorId + ", CourseID=" + CourseID + "]";
    }

    public void addSubmission(Long studentId, String answer) {
        if (studentId == null || answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Student ID and answer cannot be null or empty.");
        }

        Submissions.put(studentId, answer);
    }

}
