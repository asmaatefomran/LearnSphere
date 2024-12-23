package com.example.LMS.model;

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
    HashMap<Long, String> Submissions = new HashMap<>();
    List<Question> questions;
    int grade;

    String Feedback;
    Long instructorId;
    Long courseID;
    public long setId() {
        this.id = UserIdGenerotor.generateId();
        return this.id;
    }
    public void addSubmission(Long studentId, String answer) {
        if (studentId == null || answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Student ID and answer cannot be null or empty.");
        }

        Submissions.put(studentId, answer);
    }
}
