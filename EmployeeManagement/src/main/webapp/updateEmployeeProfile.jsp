<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.project.model.Employee" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Update Employee Profile</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <style>
    body { background: #f8f9fa; }
    .container { margin-top: 50px; }
  </style>
</head>
<body>
<%
    // Retrieve employee details passed from servlet
    
    Employee employee = (Employee) request.getAttribute("employee");
    if (employee != null) {
%>
    <h1>Update Your Profile</h1>
    <form action="FrontControllerServlet" method="post">
        <input type="hidden" name="action" value="updateEmployeeProfile">
        <input type="hidden" name="employeeId" value="<%= employee.getEmployeeId() %>">

        <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input type="text" class="form-control" name="name" value="<%= employee.getName() %>" required>
        </div>

        <div class="mb-3">
            <label for="department" class="form-label">Department</label>
            <input type="text" class="form-control" name="department" value="<%= employee.getDepartment() %>" required>
        </div>

        <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <input type="text" class="form-control" name="phone" value="<%= employee.getPhone() %>" required>
        </div>

        <button type="submit" class="btn btn-primary">Update Profile</button>
    </form>
<%
    } else {
%>
    <p>Error: Employee details not found.</p>
<%
    }
%>
    