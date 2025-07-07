<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Project</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Delete Project</h2>

    <form action="FrontControllerServlet" method="post">
        <label for="projectId">Enter Project ID to Delete:</label>
        <input type="text" id="projectId" name="projectId" required placeholder="project-001">

        <input type="hidden" name="action" value="deleteProject">

        <button type="submit">Delete Project</button>
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
