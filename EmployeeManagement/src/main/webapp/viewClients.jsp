<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Clients</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Client List</h2>

    <table>
        <thead>
            <tr>
                <th>Client ID</th>
                <th>Client Name</th>
                <th>Contact Person</th>
                <th>Client Relationship Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="client" items="${clients}">
                <tr>
                    <td>${client.clientId}</td>
                    <td>${client.contactPerson}</td>
                    <td>${client.clientRelationshipDate}</td>
                    <td>
                        <a href="FrontControllerServlet?action=viewClientDetails&clientId=${client.clientId}">View</a> |
                        <a href="FrontControllerServlet?action=editClient&clientId=${client.clientId}">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty clients}">
        <div class="alert">
            <p>No clients found.</p>
        </div>
    </c:if>

    <footer>
        <p><a href="admin-clientDashboard.jsp">Back to Dashboard</a></p>
    </footer>
</body>
</html>
