package com.project.model;

import java.util.Date;

public class LoginAttempt {
    private String email;
    private String otpCode;
    private Date attemptTime;
    private boolean success;

    // Default constructor
    public LoginAttempt() {
        this.attemptTime = new Date();  // Default to current date and time
    }

    // Constructor with parameters
    public LoginAttempt(String email, String otpCode, boolean success) {
        this.email = email;
        this.otpCode = otpCode;
        this.attemptTime = new Date();  // Default to current date and time
        this.success = success;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOtpCode() { return otpCode; }
    public void setOtpCode(String otpCode) { this.otpCode = otpCode; }

    public Date getAttemptTime() { return attemptTime; }
    public void setAttemptTime(Date attemptTime) { this.attemptTime = attemptTime; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}
