<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Client Dashboard</title>
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
     
        <h1 class="text-center my-4">PERFORM OPERATION ON CLIENT</h1>
        <div class="row justify-content-center">
            <div class="col-md-3">
                <a href="viewClients.jsp" class="btn btn-primary dashboard-button btn-block">View All Clients</a>
            </div>
            <div class="col-md-3">
                <a href="addClient.jsp" class="btn btn-success dashboard-button btn-block">Add  Client</a>
            </div>
            <div class="col-md-3">
                <a href="deleteClient.jsp" class="btn btn-success dashboard-button btn-block">DELETE Client</a>
            </div>
            <div class="col-md-3">
                <a href="updateClients.jsp" class="btn btn-success dashboard-button btn-block">Update Client</a>
            </div>
            <div class="col-md-3">
                <a href="getClientById.jsp" class="btn btn-success dashboard-button btn-block">Get Client By Id</a>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
