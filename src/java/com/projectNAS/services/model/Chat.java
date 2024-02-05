package com.projectNAS.services.model;

import java.sql.Date;

/**
 *
 * @author _
 */
public class Chat {
    private int id;
    private String message;
    private Date sentDate;
    private int userId;

    public Chat(int id, String message, Date sentDate, int userId) {
        this.id = id;
        this.message = message;
        this.sentDate = sentDate;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}
