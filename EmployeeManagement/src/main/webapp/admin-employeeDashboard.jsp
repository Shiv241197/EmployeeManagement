<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>PERFORM OPERATION ON EMPLOYEES</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .dashboard-button {
            margin: 20px;
            padding: 40px;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="container">
    <a href="adminDashboard.jsp" class="btn btn-secondary mb-3">Back to Dashboard</a>
    
        <h1 class="text-center my-4">PERFORM OPERATION ON EMPLOYEES</h1>
        <div class="row justify-content-center">
            <div class="col-md-3">
                <a href="addEmployee.jsp" class="btn btn-primary dashboard-button btn-block">Add  Employee</a>
            </div>
            <div class="col-md-3">
                <a href="viewEmployees.jsp" class="btn btn-success dashboard-button btn-block">View Employees </a>
            </div>
            <div class="col-md-3">
                <a href="assignEmployeeToProject.jsp" class="btn btn-info dashboard-button btn-block">Assign Employee to Project</a>
            </div>
            <div class="col-md-3">
                <a href="deleteEmployee.jsp" class="btn btn-success dashboard-button btn-block">Delete Employee</a>
            </div>
            <div class="col-md-3">
                <a href="editEmployee.jsp" class="btn btn-success dashboard-button btn-block">edit Employees</a>
            </div>
            <div class="col-md-3">
                <a href="releaseEmployees.jsp" class="btn btn-success dashboard-button btn-block">Release Employee</a>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
