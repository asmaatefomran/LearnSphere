package com.example.LMS.model;
import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    private Long id;
    private String message;
    private long recipientID;


    public Notification(String mes, Long recID) {

        this.message = mes;
        this.recipientID = recID;

    }


    public void setId() {

        this.id = NotificationIDGenertor.generateId();
    }

    public Long getId() {
        return id;
    }
}
