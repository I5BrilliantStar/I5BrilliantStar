package com.example.project02.entity;
public class RefreshApiResponseMessage {
    private String status;
    private String message;

    public RefreshApiResponseMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters and setters (or lombok annotations @Getter and @Setter) for the fields
}
