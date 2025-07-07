<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Assign Employee to Project</title>
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
        <h2>Assign Employee to Project</h2>
        <form action="FrontcontrollerServlet?action=assignEmployeeToProject" method="post">
            <div class="form-group">
                <label for="employeeId">Select Employee:</label>
                <select class="form-control" id="employeeId" name="employeeId" required>
                    <c:forEach var="employee" items="${employees}">
                        <option value="${employee.id}">${employee.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="projectId">Select Project:</label>
                <select class="form-control" id="projectId" name="projectId" required>
                    <c:forEach var="project" items="${projects}">
                        <option value="${project.id}">${project.name}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Assign Employee</button>
        </form>
        <br>
        <a href="admin-employeeDashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
