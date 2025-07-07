<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Dashboard</title>
    <link rel="stylesheet" href="css/styles.css"> <!-- Include your CSS file -->
</head>
<body>
    <div class="container">
        <h2>Welcome, ${client.companyName}!</h2>

        <!-- Client Information -->
        <div class="card">
            <h3>Company Details</h3>
            <p><strong>Company Name:</strong> ${client.companyName}</p>
            <p><strong>Relationship Date:</strong> <fmt:formatDate value="${client.relationshipDate}" pattern="dd-MM-yyyy"/></p>
        </div>

        <!-- Contact Persons -->
        <div class="card">
            <h3>Contact Persons</h3>
            <table border="1">
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Designation</th>
                </tr>
                <c:forEach var="contact" items="${contactPersons}">
                    <tr>
                        <td>${contact.contactName}</td>
                        <td>${contact.contactEmail}</td>
                        <td>${contact.contactPhone}</td>
                        <td>${contact.designation}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <!-- Projects Assigned -->
        <div class="card">
            <h3>Projects Assigned</h3>
            <table border="1">
                <tr>
                    <th>Project Name</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Employees Assigned</th>
                </tr>
                <c:forEach var="project" items="${projects}">
                    <tr>
                        <td>${project.projectName}</td>
                        <td><fmt:formatDate value="${project.startDate}" pattern="dd-MM-yyyy"/></td>
                        <td><fmt:formatDate value="${project.endDate}" pattern="dd-MM-yyyy"/></td>
                        <td>
                            <c:forEach var="employee" items="${project.employees}">
                                ${employee.name} (${employee.department})<br>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <!-- Logout Button -->
        <a href="FrontControllerServlet?action=logout" class="btn btn-danger">Logout</a>
    </div>
</body>
</html>
