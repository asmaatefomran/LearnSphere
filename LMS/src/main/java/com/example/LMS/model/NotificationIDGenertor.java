package com.example.LMS.model;

public class NotificationIDGenertor {
        private static Long currentId = 0L;
        public static Long generateId() {
            return ++currentId;
        }

}
