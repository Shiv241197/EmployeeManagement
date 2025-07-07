package com.project.model;

import java.util.Date;

public class Client {
    private String clientId; // client-001, client-002, ...
    private String companyName; // This is the client name, change as needed
    private String contactPerson;
    private String email; // Email of the client
    private String phone; // Added phone field
    private Date relationshipDate;

    // Default Constructor
    public Client() {
        this.relationshipDate = new Date(); // Default to current date
    }

    // Constructor with parameters
    public Client(String clientId, String companyName, String contactPerson, String email, String phone, Date relationshipDate) {
        this.clientId = clientId;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.email = email;
        this.phone = phone;
        this.relationshipDate = (relationshipDate != null) ? relationshipDate : new Date();
    }

    // Getters and Setters
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; } // Getter for phone
    public void setPhone(String phone) { this.phone = phone; } // Setter for phone

    public Date getRelationshipDate() { return relationshipDate; }
    public void setRelationshipDate(Date relationshipDate) { 
        this.relationshipDate = (relationshipDate != null) ? relationshipDate : new Date(); 
    }
}
