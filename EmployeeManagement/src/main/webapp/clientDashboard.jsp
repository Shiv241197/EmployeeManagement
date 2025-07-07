<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Dashboard</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
    <link rel="icon" href="images/favicon.ico"> <!-- Optional: Your website icon -->
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar">
        <div class="navbar-container">
            <a href="clientlogin.jsp" class="navbar-logo">Client Dashboard</a>
            <ul class="navbar-links">
                <li><a href="MainDashboard.jsp">Dashboard</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
        </div>
    </nav>

    <!-- Dashboard Header -->
    <header class="dashboard-header">
        <h1>Welcome, ${client.companyName}</h1>
        <p>Your client profile and projects overview.</p>
    </header>

    <!-- Main Dashboard Section -->
    <div class="dashboard-container">

        <!-- Client Profile Section -->
        <section class="card profile-card">
            <h2>Client Profile</h2>
            <table class="profile-table">
                <tr>
                    <th>Client ID:</th>
                    <td>${client.clientId}</td>
                </tr>
                <tr>
                    <th>Company Name:</th>
                    <td>${client.companyName}</td>
                </tr>
                <tr>
                    <th>Email:</th>
                    <td>${client.email}</td>
                </tr>
                <tr>
                    <th>Phone:</th>
                    <td>${client.phone}</td>
                </tr>
                <tr>
                    <th>Contact Person:</th>
                    <td>${client.contactPerson}</td>
                </tr>
                <tr>
                    <th>Relationship Date:</th>
                    <td>${client.relationshipDate}</td>
                </tr>
            </table>
        </section>

        <!-- Client Projects Section -->
        <section class="card projects-card">
            <h2>Assigned Projects</h2>

            <c:if test="${not empty client.projects}">
                <div class="projects-list">
                    <c:forEach var="project" items="${client.projects}">
                        <div class="project-item">
                            <h3>${project.projectName}</h3>
                            <p><strong>Start Date:</strong> ${project.startDate}</p>
                            <p><strong>End Date:</strong> ${project.endDate}</p>
                            <p><strong>Status:</strong> ${project.status}</p>
                            <a href="projectDetails.jsp?projectId=${project.projectId}" class="view-project-btn">View Details</a>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <c:if test="${empty client.projects}">
                <p>No projects assigned yet.</p>
            </c:if>
        </section>

    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2025 Company Name. All rights reserved.</p>
    </footer>

</body>
</html>
