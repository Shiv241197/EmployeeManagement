<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Project Dashboard</title>
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
        <h1 class="text-center my-4">PERFORM OPERATION ON PROJECT</h1>
        <div class="row justify-content-center">
            <div class="col-md-3">
                <a href="deleteProject.jsp" class="btn btn-primary dashboard-button btn-block">Delete Project</a>
            </div>
            <div class="col-md-3">
                <a href="addProject.jsp" class="btn btn-success dashboard-button btn-block">Add Project</a>
            </div>
            <div class="col-md-3">
                <a href="updateProject.jsp" class="btn btn-primary dashboard-button btn-block">Update Project</a>
            </div>
            <div class="col-md-3">
                <a href="getProjectById.jsp" class="btn btn-primary dashboard-button btn-block">Get Project By ID</a>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
