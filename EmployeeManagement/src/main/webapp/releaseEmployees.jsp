<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Release Employees</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container {
            margin-top: 50px;
            max-width: 800px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="text-center">Release Employees from Projects</h2>
        
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Project</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="employee" items="${assignedEmployees}">
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.name}</td>
                        <td>${employee.email}</td>
                        <td>${employee.projectName}</td>
                        <td>
                            <a href="FrontControllerServlet?action=releaseEmployee&id=${employee.id}" class="btn btn-danger btn-sm">Release</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="admin-employeeDashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
