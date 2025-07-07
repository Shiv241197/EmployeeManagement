package com.project.model;

import java.util.Date;

public class Project {
    private String projectId; // project-001, project-002, ...
    private String name;
    private Date startDate;
    private Date endDate;
    private String clientId;

    // Default constructor
    public Project() {}

    // Constructor with parameters
    public Project(String projectId, String name, Date startDate, Date endDate, String clientId) {
        this.projectId = projectId;
        this.name = name;
        this.startDate = startDate != null ? startDate : new Date();  // Default to current date if null
        this.endDate = endDate;
        this.clientId = clientId;
    }

    // Getters and Setters
    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
}

