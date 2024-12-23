package com.example.LMS.service;

import com.example.LMS.model.*;
import com.example.LMS.repository.CourseRepo;
import com.example.LMS.repository.NotificationRepo;
import com.example.LMS.repository.StudentRepo;
import com.example.LMS.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserService userService;
    @InjectMocks
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @MockBean
    private NotificationService notificationService;

    @MockBean
    private QuizService quizService;

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private CourseRepo courseRepo;

    @Mock
    private NotificationRepo notificationRepo;
    @Mock
    private StudentRepo studentRepo;

    private Student student;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("student", "student@example.com", "password123", "student");
        student = new Student("malak", "malak.@gmail.com", "123456", "student");
    }

    @Test
    void attend() throws Exception {
        Course course = new Course("cpp", "C++ Programming", "122");
        Lesson lesson = new Lesson("cp", 1);
        userRepository.saveUser(student);
        courseRepo.save(course);
        courseRepo.addLesson(1L, lesson);
        var result = mockMvc.perform(MockMvcRequestBuilders.get("/api/student/attendlesson")
                        .param("Studid", "23")
                        .param("Lessonname", "cp")
                        .param("Couid", "1"))
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        assertTrue(responseBody.contains("cp"));
        assertEquals("{\"name\":\"cp\",\"questions\":[],\"attendees\":[{\"id\":23,\"name\":\"malak\",\"email\":\"malak.@gmail.com\",\"password\":\"123456\",\"role\":\"student\",\"notififcations\":null,\"major\":null,\"courses\":null,\"gpa\":0.0,\"graduationYear\":0}],\"instructorId\":null,\"courseId\":1}", responseBody);
    }

}
