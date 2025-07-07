<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Project</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Update Project</h2>

    <form action="FrontControllerServlet" method="post">
        <label for="projectId">Project ID:</label>
        <input type="text" id="projectId" name="projectId" required placeholder="project-001" value="${project.projectId}" readonly>

        <label for="projectName">Project Name:</label>
        <input type="text" id="projectName" name="projectName" required placeholder="Enter Project Name" value="${project.projectName}">

        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" required value="${project.startDate}">

        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" required value="${project.endDate}">

        <label for="clientId">Client ID:</label>
        <input type="text" id="clientId" name="clientId" required placeholder="client-001" value="${project.clientId}">

        <input type="hidden" name="action" value="updateProject">

        <button type="submit">Update Project</button>
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
