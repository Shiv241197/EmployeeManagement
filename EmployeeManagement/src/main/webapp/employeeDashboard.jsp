<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.project.model.Employee, com.project.model.Project" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Employee Dashboard</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <style>
    body { background: #f8f9fa; }
    .dashboard-container { margin-top: 50px; }
    .card-header { font-weight: bold; }
  </style>
</head>
<body>
<%
    
    
    // Retrieve the employee and project details passed from the servlet.
    Employee employee = (Employee) request.getAttribute("employee");
    Project project = (Project) request.getAttribute("project");
    String email = (String) session.getAttribute("employeeEmail");
%>
  <div class="container dashboard-container">
    <div class="d-flex justify-content-between align-items-center mb-4">
       <h1>Employee Dashboard</h1>
       <a href="FrontControllerServlet?action=logout" class="btn btn-danger">Logout</a>
    </div>
    
    <!-- Employee Details Section -->
    <div class="card mb-4 shadow">
      <div class="card-header bg-primary text-white">
        Employee Details
      </div>
      <div class="card-body">
         <div class="row">
           <div class="col-md-6">
              <p><strong>Name:</strong> <%= (employee != null ? employee.getName() : "N/A") %></p>
              <p><strong>Department:</strong> <%= (employee != null ? employee.getDepartment() : "N/A") %></p>
              <p><strong>Email:</strong> <%= email %></p>
           </div>
           <div class="col-md-6">
              <p><strong>Phone:</strong> <%= (employee != null ? employee.getPhone() : "N/A") %></p>
              <p><strong>Date of Joining:</strong> <%= (employee != null ? employee.getDateOfJoining() : "N/A") %></p>
           </div>
         </div>
         <a href="updateEmployeeProfile.jsp" class="btn btn-secondary mt-3">Update Profile</a>
      </div>
    </div>
    
    <!-- Project Details Section -->
    <div class="card mb-4 shadow">
      <div class="card-header bg-success text-white">
        Project Details
      </div>
      <div class="card-body">
         <% if (project != null) { %>
         <div class="row">
           <div class="col-md-6">
              <p><strong>Project Name:</strong> <%= project.getName() %></p>
              <p><strong>Project ID:</strong> <%= project.getProjectId() %></p>
           </div>
           <div class="col-md-6">
              <p><strong>Start Date:</strong> <%= project.getStartDate() %></p>
              <p><strong>End Date:</strong> <%= project.getEndDate() != null ? project.getEndDate() : "Ongoing" %></p>
              <p><strong>Client ID:</strong> <%= project.getClientId() %></p>
           </div>
         </div>
         <a href="viewProjectDetails.jsp" class="btn btn-info mt-3">View Project Details</a>
         <% } else { %>
         <p>No project assigned. You are currently on the bench.</p>
         <% } %>
      </div>
    </div>
    
    <!-- Additional Options Section -->
    <div class="card shadow">
      <div class="card-header bg-warning text-dark">
        Additional Options  
      </div>
      <div class="card-body">
         <p>You can check your assigned tasks, update your profile, or contact your manager for project assignment details.</p>
         <a href="viewTasks.jsp" class="btn btn-primary">View Tasks</a>
         <a href="contactManager.jsp" class="btn btn-outline-secondary">Contact Manager</a>
      </div>
    </div>
  </div>
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
