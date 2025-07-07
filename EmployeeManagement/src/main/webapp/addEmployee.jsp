<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Employee</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .form-container {
            margin-top: 50px;
        }
    </style>
</head>
<body>
    <div class="container form-container">
        <h2>Add New Employee</h2>
        <form action="FrontControllerServlet?action=addEmployee" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" class="form-control" id="phone" name="phone" required>
            </div>
            <div class="form-group">
                <label for="department">Department:</label>
                <input type="text" class="form-control" id="department" name="department" required>
            </div>
            <div class="form-group">
                <label for="dateOfJoining">Date of Joining:</label>
                <input type="date" class="form-control" id="dateOfJoining" name="dateOfJoining" required>
            </div>
            <!-- Project ID Dropdown -->
            <div class="form-group">
                <label for="projectId">Project Id</label>
                <select class="form-control" id="projectId" name="projectId">
                    <option value=""></option>
                    <!-- Loop through existing projects (from the database) -->
                    <c:forEach var="employee" items="${employees}">
                        <option value="${employee.projectId}">${employee.projectId}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add Employee</button>
        </form>
        <br>
        <a href="viewEmployees.jsp" class="btn btn-secondary">Back to Employee List</a>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
