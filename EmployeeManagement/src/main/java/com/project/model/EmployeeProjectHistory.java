
	package com.project.model;

import java.util.Date;

public class EmployeeProjectHistory {
    private String employeeId;
    private String projectId;
    private Date startDate;
    private Date endDate;

    // Default constructor
    public EmployeeProjectHistory() {}

    // Constructor with parameters
    public EmployeeProjectHistory(String employeeId, String projectId, Date startDate, Date endDate) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.startDate = startDate != null ? startDate : new Date();  // Default to current date if not provided
        this.endDate = endDate;
    }

    // Getters and Setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
}
