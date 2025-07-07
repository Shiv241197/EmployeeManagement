package com.project.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Employee {
	
    
    private String employeeId; // JTC-001, JTC-002, ...
    private String name;
    private String department;
    private String email;
    private String phone;
    private LocalDateTime dateOfJoining;
    private String projectId; // Can be null if employee is on the bench

    public Employee() {}

    public Employee(String employeeId, String name, String department, String email, String phone, LocalDateTime dateOfJoining, String projectId) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.email = email;
        this.phone = phone;
        this.dateOfJoining = dateOfJoining != null ? dateOfJoining : LocalDateTime.now(); // Default to current timestamp
        this.projectId = projectId;
    }

    // Overloaded constructor for creating a new employee without a project assignment
    public Employee(String name, String department, String email, String phone) {
        this(null, name, department, email, phone, LocalDateTime.now(), null);
    }

	public Employee(String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}

	// Getters and Setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public LocalDateTime getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(LocalDateTime dateOfJoining) { this.dateOfJoining = dateOfJoining; }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

	public void setDateOfJoining(Date date) {
		// TODO Auto-generated method stub
		
	}

}
