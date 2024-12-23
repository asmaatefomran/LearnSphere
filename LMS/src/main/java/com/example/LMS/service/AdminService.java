package com.example.LMS.service;

import com.example.LMS.model.Admin;
import com.example.LMS.model.Course;
import com.example.LMS.model.User;
import com.example.LMS.repository.CourseRepo;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AdminService {

    private Admin admin;

    @Autowired
    private CourseRepo courseRepository;

    public AdminService() {
        admin = new Admin("Admin Name", "admin@example.com", "admin123", "admin");
    }

    public String viewUserRoles(String email) {
        return email.equals(admin.getEmail()) ? admin.getRole() : "User not found!";
    }

    public List<String> viewEnrolledStudents(Long courseId) {
        return admin.getCourseEnrolledStudents().get(courseId);
    }


    public void manageAdminProfile(String name, String email, String password) {
        admin.setName(name);
        admin.setEmail(email);
        admin.setPassword(password);
    }

    public String generatePreformedCharts() {
        // Fetch data from the database
        List<Course> courses = courseRepository.findAll();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Course course : courses) {
            int enrolledStudents = course.getEnrolledStudentIds().size(); 
            dataset.addValue(enrolledStudents, "Students", course.getTitle());
        }

        // Create the bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Course Enrollments",  
                "Courses",           
                "Number of Students",
                dataset               // Data
        );

        try {
            File chartFile = new File("CourseEnrollmentChart.png");
            ChartUtils.saveChartAsPNG(chartFile, barChart, 800, 600);
            return "Preformed charts generated successfully. Check the file: CourseEnrollmentChart.png";
        } catch (IOException e) {
            return "Failed to generate preformed charts: " + e.getMessage();
        }
    }
}
