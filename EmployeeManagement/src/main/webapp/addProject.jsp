<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Project</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Add New Project</h2>

    <form action="FrontControllerServlet" method="post">
        <label for="projectName">Project Name:</label>
        <input type="text" id="projectName" name="projectName" required placeholder="Enter Project Name">

        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" required>

        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" required>

        <label for="clientId">Client ID:</label>
        <input type="text" id="clientId" name="clientId" required placeholder="client-001">

        <input type="hidden" name="action" value="addProject">

        <button type="submit">Add Project</button>
    </form>

    <c:if test="${not empty message}">
        <div class="alert">
            <p>${message}</p>
        </div>
    </c:if>

    <footer>
        <p><a href="admin-projectDashboard.jsp">Back to Dashboard</a></p>
    </footer>
</body>
</html>
