package com.example.LMS.repository;
import com.example.LMS.model.Notification;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class NotificationRepo {
    private final Map<Long, List<Notification>> notificationMap = new HashMap<>();

    public Notification createNotification(Notification n){
            List<Notification> notifications = notificationMap.getOrDefault(n.getRecipientID(), new ArrayList<>());
            notifications.add(n);
            notificationMap.put(n.getRecipientID(), notifications);
            return n;

    }
    public Optional<List<Notification>> getLatestNotification(Long recipient) {

        return Optional.ofNullable(notificationMap.get(recipient));
    }
}
