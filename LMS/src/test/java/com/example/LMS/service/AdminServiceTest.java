package com.example.LMS.service;

import com.example.LMS.model.Course;
import com.example.LMS.repository.CourseRepo;
import org.jfree.chart.ChartUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class AdminServiceTest {

    @Mock
    private CourseRepo courseRepository;

    @InjectMocks
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void viewUserRoles_ShouldReturnAdminRole() {
        String role = adminService.viewUserRoles("admin@example.com");
        assertEquals("admin", role);
    }

    @Test
    void viewUserRoles_ShouldReturnUserNotFound() {
        String role = adminService.viewUserRoles("unknown@example.com");
        assertEquals("User not found!", role);
    }

    @Test
    void manageEnrolledStudents_ShouldAddStudentsToCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setTitle("Math 101");
        course.setEnrolledStudentIds(Arrays.asList("Student A", "Student B"));

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));


        List<String> newStudents = Arrays.asList("Student C", "Student D", "Student A");
        adminService.manageEnrolledStudents(1L, newStudents);
        ArgumentCaptor<Course> courseCaptor = ArgumentCaptor.forClass(Course.class);
        verify(courseRepository).save(courseCaptor.capture());

        Course savedCourse = courseCaptor.getValue();
        assertTrue(savedCourse.getEnrolledStudentIds().containsAll(Arrays.asList("Student A", "Student B", "Student C", "Student D")));
        assertEquals(4, savedCourse.getEnrolledStudentIds().size());
    }

    @Test
    void manageAdminProfile_ShouldUpdateAdminDetails() {
        adminService.manageAdminProfile("New Admin", "newadmin@example.com", "newpassword");
        String role = adminService.viewUserRoles("newadmin@example.com");
        assertEquals("admin", role);
    }

    @Test
    void generatePreformedCharts_ShouldReturnSuccessMessage() throws IOException {
        Course course1 = new Course();
        course1.setTitle("Math 101");
        course1.setEnrolledStudentIds(Arrays.asList("Student A", "Student B"));

        Course course2 = new Course();
        course2.setTitle("Science 202");
        course2.setEnrolledStudentIds(Arrays.asList("Student C"));

        List<Course> courses = Arrays.asList(course1, course2);
        when(courseRepository.findAll()).thenReturn(courses);

        String result = adminService.generatePreformedCharts();

        assertTrue(result.contains("Preformed charts generated successfully"));
        File chartFile = new File("CourseEnrollmentChart.png");
        assertTrue(chartFile.exists());
        chartFile.delete();
    }

    @Test
    void generatePreformedCharts_ShouldHandleIOException() throws IOException {
        Course course1 = new Course();
        course1.setTitle("Math 101");
        course1.setEnrolledStudentIds(Arrays.asList("Student A"));
    
        when(courseRepository.findAll()).thenReturn(List.of(course1));
    
        // Mocking ChartUtils to throw an IOException
        try (MockedStatic<ChartUtils> mockedChartUtils = mockStatic(ChartUtils.class)) {
            mockedChartUtils.when(() -> ChartUtils.saveChartAsPNG(any(File.class), any(), anyInt(), anyInt()))
                            .thenThrow(IOException.class);
    
            // Act
            String result = adminService.generatePreformedCharts();
            assertTrue(result.contains("Failed to generate preformed charts"));
        }
    }
}
