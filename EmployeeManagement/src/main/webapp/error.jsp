<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Error</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <style>
    body { background: #f8f9fa; display: flex; justify-content: center; align-items: center; height: 100vh; }
    .container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); text-align: center; }
  </style>
</head>
<body>
  <div class="container">
    <h2 class="text-danger">An Error Occurred</h2>
    <p><%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "An unexpected error occurred." %></p>
    <a href="employeeLogin.jsp" class="btn btn-primary">Back to Login</a>
  </div>
</body>
</html>
