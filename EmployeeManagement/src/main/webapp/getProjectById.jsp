<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Project Details</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>View Project Details</h2>

    <form action="FrontControllerServlet" method="get">
        <label for="projectId">Enter Project ID to View:</label>
        <input type="text" id="projectId" name="projectId" required placeholder="project-001">

        <input type="hidden" name="action" value="getProjectById">

        <button type="submit">View Project</button>
    </form>

    <c:if test="${not empty project}">
        <div class="project-details">
            <h3>Project Information</h3>
            <p><strong>Project ID:</strong> ${project.projectId}</p>
            <p><strong>Project Name:</strong> ${project.projectName}</p>
            <p><strong>Start Date:</strong> ${project.startDate}</p>
            <p><strong>End Date:</strong> ${project.endDate}</p>
            <p><strong>Client ID:</strong> ${project.clientId}</p>
        </div>
    </c:if>

    <c:if test="${empty project}">
        <div class="alert">
            <p>Project not found. Please check the Project ID and try again.</p>
        </div>
    </c:if>

    <footer>
        <p><a href="admin-projectDashboard.jsp">Back to Dashboard</a></p>
    </footer>
</body>
</html>
