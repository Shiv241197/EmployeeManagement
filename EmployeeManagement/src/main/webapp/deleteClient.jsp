<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Client</title>
    <link rel="stylesheet" href="css/styles.css"> <!-- Include your CSS file -->
</head>
<body>
    <div class="container">
        <h2>Delete Client</h2>
        <p>Are you sure you want to delete the client <strong>${client.name}</strong>?</p>
        
        <form action="FrontControllerServlet" method="post">
            <input type="hidden" name="action" value="deleteClient">
            <input type="hidden" name="clientId" value="${client.clientId}"> <!-- Assuming clientId is available in the request -->
            
            <button type="submit" class="btn btn-danger">Yes, Delete Client</button>
            <a href="viewClients.jsp" class="btn btn-secondary">No, Go Back</a>
        </form>
    </div>
</body>
</html>
