package com.example.LMS.model;

public class CourseIdGenerator {
    private static Long currentId = 0L;
    public static Long generateId() {
        return ++currentId;
}
}
