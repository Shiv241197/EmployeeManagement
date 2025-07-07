<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
            max-width: 600px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Edit Employee</h2>
        
        <form action="FrontControllerServlet" method="post">
            <input type="hidden" name="action" value="updateEmployee">
            <input type="hidden" name="id" value="${employee.id}">
            
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="${employee.name}" required>
            </div>
            
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value="${employee.email}" required readonly>
            </div>
            
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" class="form-control" id="phone" name="phone" value="${employee.phone}" required>
            </div>
            
            <div class="form-group">
                <label for="role">Role:</label>
                <input type="text" class="form-control" id="role" name="role" value="${employee.role}" required>
            </div>
            
            <button type="submit" class="btn btn-primary">Update Employee</button>
            <a href="FrontControllerServlet?action=viewEmployees" class="btn btn-secondary">Cancel</a>
            <a href="admin-employeeDashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
        </form>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
