<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Employee</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Update Employee</h2>

    <form action="FrontControllerServlet" method="post">
        <label for="employeeId">Employee ID:</label>
        <input type="text" id="employeeId" name="employeeId" required placeholder="JTC-001" value="${employee.employeeId}" readonly>

        <label for="employeeName">Employee Name:</label>
        <input type="text" id="employeeName" name="employeeName" required placeholder="Enter Employee Name" value="${employee.employeeName}">

        <label for="employeeDept">Employee Department:</label>
        <input type="text" id="employeeDept" name="employeeDept" required placeholder="Enter Employee Department" value="${employee.employeeDept}">

        <label for="employeeEmail">Employee Email:</label>
        <input type="email" id="employeeEmail" name="employeeEmail" required placeholder="Enter Employee Email" value="${employee.employeeEmail}">

        <label for="employeePhone">Employee Phone:</label>
        <input type="text" id="employeePhone" name="employeePhone" required placeholder="Enter Employee Phone" value="${employee.employeePhone}">

        <label for="dateOfJoining">Date of Joining:</label>
        <input type="date" id="dateOfJoining" name="dateOfJoining" required value="${employee.dateOfJoining}">

        <input type="hidden" name="action" value="updateEmployee">

        <button type="submit">Update Employee</button>
    </form>

    <c:if test="${not empty message}">
        <div class="alert">
            <p>${message}</p>
        </div>
    </c:if>

    <footer>
        <p><a href="admin-employeeDashboard.jsp">Back to Dashboard</a></p>
    </footer>
</body>
</html>
