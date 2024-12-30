package com.example.LMS.service;

import com.example.LMS.model.Notification;
import com.example.LMS.model.User;
import com.example.LMS.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepo notificationRepo;
    @Autowired
    private UserService userService;
    public Notification AddNotification(String message,Long recipientID){
        Notification n = new Notification(message,recipientID);
        n.setId();
        User userOptional = userService.getUserbyId(recipientID);
            if (userOptional.getNotifications() == null) {
        userOptional.setNotifications(new ArrayList<>());  // Initialize if it's null
    }
        userOptional.getNotifications().add(n);
        userService.update(userOptional);
        return notificationRepo.createNotification(n);
    }
    public Optional<List<Notification>> ShowNotifactions(long recID){
        return notificationRepo.getLatestNotification(recID);
    }
    public void notifyInstructorForEnrollment(Long instructorID, String message) {
        AddNotification(message, instructorID);  // Send the notification to the instructor
    }

}
