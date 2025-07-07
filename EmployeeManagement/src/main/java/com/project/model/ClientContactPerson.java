package com.project.model;

public class ClientContactPerson {
    private int contactId; // Unique identifier for each contact person
    private String clientId; // Client associated with the contact
    private String name;
    private String email;
    private String phone;
    private String designation;

    // Default Constructor
    public ClientContactPerson() {}

    // Constructor with parameters
    public ClientContactPerson(int contactId, String clientId, String name, String email, String phone, String designation) {
        this.contactId = contactId;
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.designation = designation;
    }

    // Getters and Setters
    public int getContactId() { return contactId; }
    public void setContactId(int contactId) { this.contactId = contactId; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
}
