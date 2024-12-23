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
        // Initialize an admin user
        admin = new Admin("Admin Name", "admin@example.com", "admin123", "admin");
    }

    // View user roles
    public String viewUserRoles(String email) {
        return email.equals(admin.getEmail()) ? admin.getRole() : "User not found!";
    }

    // Manage enrolled students
    public void manageEnrolledStudents(Long courseId, List<String> students) {
        // Fetch the course by ID from the repository
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with ID " + courseId + " not found"));
    
        // Initialize the enrolled student list if it is null
        if (course.getEnrolledStudentIds() == null) {
            course.setEnrolledStudentIds(new ArrayList<>());
        }
    
        // Add the new students while avoiding duplicates
        Set<String> uniqueStudents = new HashSet<>(course.getEnrolledStudentIds());
        uniqueStudents.addAll(students);
    
        // Update the course with the new list
        course.setEnrolledStudentIds(new ArrayList<>(uniqueStudents));
    
        // Manually update the course in the repository
        courseRepository.save(course);
    }
    

    // View enrolled students in each course
    public List<String> viewEnrolledStudents(Long courseId) {
        return admin.getCourseEnrolledStudents().get(courseId);
    }

    // Manage admin profile
    public void manageAdminProfile(String name, String email, String password) {
        admin.setName(name);
        admin.setEmail(email);
        admin.setPassword(password);
    }

    public String generatePreformedCharts() {
        // Fetch data from the database
        List<Course> courses = courseRepository.findAll();

        // Create a dataset based on the data
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Course course : courses) {
            int enrolledStudents = course.getEnrolledStudentIds().size(); // Assuming Course has a list of enrolled students
            dataset.addValue(enrolledStudents, "Students", course.getTitle());
        }

        // Create the bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Course Enrollments",  // Chart title
                "Courses",            // X-axis label
                "Number of Students", // Y-axis label
                dataset               // Data
        );

        // Save the chart as an image file
        try {
            File chartFile = new File("CourseEnrollmentChart.png");
            ChartUtils.saveChartAsPNG(chartFile, barChart, 800, 600);
            return "Preformed charts generated successfully. Check the file: CourseEnrollmentChart.png";
        } catch (IOException e) {
            return "Failed to generate preformed charts: " + e.getMessage();
        }
    }
}
