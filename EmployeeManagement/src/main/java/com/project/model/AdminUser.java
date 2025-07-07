package com.project.model;

public class AdminUser {
    private String adminEmail;
    private String hashedPassword; // Store hashed password instead of plain text

    // Constructors
    public AdminUser() {}

    public AdminUser(String adminEmail, String hashedPassword) {
        this.adminEmail = adminEmail;
        this.hashedPassword = hashedPassword;
    }

    // Getters and Setters
    public String getAdminEmail() { return adminEmail; }
    public void setAdminEmail(String adminEmail) { this.adminEmail = adminEmail; }

    public String getHashedPassword() { return hashedPassword; }
    public void setHashedPassword(String hashedPassword) { this.hashedPassword = hashedPassword; }

	public void setAdminPassword(String string) {
		// TODO Auto-generated method stub
		
	}

	public String getAdminPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	}

