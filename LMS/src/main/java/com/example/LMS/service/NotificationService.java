package com.example.LMS.service;

import com.example.LMS.model.Notification;
import com.example.LMS.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepo notificationRepo;
    public Notification AddNotification(String message,Long recipientID){
        Notification n = new Notification(message,recipientID);
        n.setId();
        return notificationRepo.createNotification(n);
    }
    public Optional<List<Notification>> ShowNotifactions(long recID){
        return notificationRepo.getLatestNotification(recID);
    }

}
