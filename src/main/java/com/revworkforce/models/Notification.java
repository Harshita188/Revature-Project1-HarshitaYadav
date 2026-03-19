 package com.revworkforce.models;

public class Notification {

    private int id; // ye add karo
    private int employeeId;
    private String message;
    private boolean read;

    public Notification(int employeeId, String message) {
        this.employeeId = employeeId;
        this.message = message;
        this.read = false;
    }

    // Getter & Setter for id
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public String getMessage() { return message; }
    public boolean isRead() { return read; }

    public void markRead() { this.read = true; }
    
    public void setRead(boolean read) { this.read = read; } // optional
}