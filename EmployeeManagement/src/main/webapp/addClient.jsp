<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Client</title>
    <link rel="stylesheet" href="css/styles.css"> <!-- Include your CSS file -->
</head>
<body>
    <div class="container">
        <h2>Add New Client</h2>
        <form action="FrontControllerServlet" method="post">
            <input type="hidden" name="action" value="addClient">

            <div class="form-group">
                <label for="clientName">Client Name (Company Name):</label>
                <input type="text" id="clientName" name="clientName" required="true" class="form-control">
            </div>

            <div class="form-group">
                <label for="contactPersonName">Contact Person Name:</label>
                <input type="text" id="contactPersonName" name="contactPersonName" required="true" class="form-control">
            </div>

            <div class="form-group">
                <label for="contactPersonEmail">Contact Person Email:</label>
                <input type="email" id="contactPersonEmail" name="contactPersonEmail" required="true" class="form-control">
            </div>

            <div class="form-group">
                <label for="contactPersonPhone">Contact Person Phone:</label>
                <input type="tel" id="contactPersonPhone" name="contactPersonPhone" required="true" class="form-control">
            </div>

            <div class="form-group">
                <label for="contactPersonDesignation">Contact Person Designation:</label>
                <input type="text" id="contactPersonDesignation" name="contactPersonDesignation" required="true" class="form-control">
            </div>

            <div class="form-group">
                <label for="relationshipDate">Client Relationship Date:</label>
                <input type="date" id="relationshipDate" name="relationshipDate" class="form-control">
            </div>

            <button type="submit" class="btn btn-primary">Add Client</button>
            <a href="viewClients.jsp" class="btn btn-secondary">Back to Client List</a>
        </form>
    </div>
</body>
</html>
