package com.example.LMS.model;
import com.example.LMS.model.StudentGradeFeedback;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Assesment {
    Long id;
    List<HashMap<Long, StudentGradeFeedback>> students; // student ID, grade, and feedback
    List<Question> questions;
    int grade;
    Long instructorId;
    Long CourseID;

    public long setId() {
        this.id = UserIdGenerotor.generateId();
        return this.id;
    }

    @Override
    public String toString() {
        return "Assesment [id=" + id + ", students=" + students + ", questions=" + questions + ", grade=" + grade
                + ", instructorId=" + instructorId + ", CourseID=" + CourseID + "]";
    }

 
}
