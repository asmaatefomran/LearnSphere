# LearnSphere - Full Stack Learning Management System

## Table of Contents
- [Project Overview](#project-overview)
- [Features ](#Features )
- [Used Technologies](#used-technologies)
- [Technical Requirements](#technical-requirements)


---

## Project Overview

**LearnSphere** is a web-based platform designed for managing and organizing online courses and assessments for students and instructors. It is a full-stack application with a Spring Boot back-end and a dynamic front-end for a seamless user experience. The project focuses on course creation, user management, assessments, performance tracking, and intuitive user interfaces.

---


## Features  

### 1. User Management  
- **User Types**: Admin, Instructor, Student.  
- **Admin**: Manages overall system settings, creates users, and manages courses.  
- **Instructor**:  
  - Creates courses and manages course content.  
  - Adds assignments and quizzes, grades students, and manages enrollments.  
- **Student**:  
  - Enrolls in courses, accesses materials, submits assignments, and takes quizzes.  

**Features**:  
- Role-based User Registration and Login.  
- Profile Management (view/update profile information).  

---

### 2. Course Management  
- **Course Creation**:  
  - Instructors can create courses with title, description, duration, and multimedia content.  
  - Courses consist of multiple lessons.  
- **Enrollment Management**:  
  - Students can browse and enroll in courses.  
  - Admins and instructors can view enrolled students per course.  
- **Attendance Management**:  
  - OTP-based attendance tracking per lesson.  

---

### 3. Assessment & Grading  
- **Quiz Creation**:  
  - Instructors can create quizzes with question types like MCQ, true/false, and short answers.  
  - Quizzes use a question bank and support randomized selection.  
- **Assignment Submission**:  
  - Students can upload files as assignments for instructor review.  
- **Grading and Feedback**:  
  - Instructors grade assignments, and students receive feedback for quizzes and assignments.  

---

### 4. Performance Tracking  
- **Student Progress**:  
  - Instructors can monitor quiz scores, assignment submissions, and attendance records.  

---

### 5. Notifications  
- **System Notifications**:  
  - Students receive notifications for enrollment confirmations, graded assignments, and updates.  
  - Instructors are notified of student enrollments and other course-related updates.  
- **Email Notifications** (Bonus):  
  - Notifications for enrollment, grades, and course updates via email.  

---

### 6. Bonus Features  
- **Role-Based Access Control**:  
  - Spring Security for authentication and authorization.  
  - Role-specific permissions for users.  
- **Performance Analytics**:  
  - Generate reports on grades and attendance.  
  - Visualize progress and course completion using charts.  

---

---

## Used Technologies

### Backend
- **Java**: Programming language for business logic.
- **Spring Boot**: Framework for RESTful API development.
- **Spring Security**: Authentication and authorization for role-based access control.
- **JPA/Hibernate**: For database ORM (Object-Relational Mapping).
- **MySQL**: Relational database management.
- **JUnit**: Unit testing for the back-end.

### Front-End (planned)
- **React.js**: Front-end library/framework for building dynamic user interfaces.
- **Tailwind CSS**: Utility-first CSS framework for responsive and modern designs.
- **Axios/Fetch API**: For consuming RESTful APIs.

### Integration & Version Control
- **Git**: For version control and collaboration.
- **Postman**: For API testing and debugging.

---

## Technical Requirements

### Backend
- **Language**: Java
- **Framework**: Spring Boot
- **Database**: MySQL

### Front-End
- **Framework/Library**: React.js
- **Styling**: Tailwind CSS, Bootstrap

### Integration, Testing, & Deployment
- **Unit Testing**: JUnit (back-end), Jest (front-end)
- **Version Control**: Git





