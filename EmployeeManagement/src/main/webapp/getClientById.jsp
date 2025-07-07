<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Get Client By ID</title>
    <link rel="stylesheet" href="css/styles.css"> <!-- Include your CSS file -->
</head>
<body>
    <div class="container">
        <h2>Get Client Details</h2>
        
        <!-- Form to search for client by ID -->
        <form action="FrontControllerServlet" method="get">
            <input type="hidden" name="action" value="getClientById">
            <div class="form-group">
                <label for="clientId">Client ID:</label>
                <input type="text" id="clientId" name="clientId" required="true" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">Get Client</button>
        </form>

        <hr>

        <!-- Display client details if available -->
        <c:if test="${not empty client}">
            <h3>Client Details</h3>
            <p><strong>Client ID:</strong> ${client.clientId}</p>
            <p><strong>Client Name:</strong> ${client.clientName}</p>
            <p><strong>Contact Person Name:</strong> ${client.contactPersonName}</p>
            <p><strong>Contact Person Email:</strong> ${client.contactPersonEmail}</p>
            <p><strong>Contact Person Phone:</strong> ${client.contactPersonPhone}</p>
            <p><strong>Contact Person Designation:</strong> ${client.contactPersonDesignation}</p>
            <p><strong>Relationship Date:</strong> <fmt:formatDate value="${client.relationshipDate}" pattern="yyyy-MM-dd" /></p>
        </c:if>

        <!-- Display error message if client not found -->
        <c:if test="${empty client}">
            <p>No client found with the provided ID.</p>
        </c:if>
        <p><a href="admin-clientDashboard.jsp">Back to Dashboard</a></p>
    </div>
</body>
</html>
