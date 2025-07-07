<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Employee Login</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <style>
    body { 
      background: #f4f4f4; 
      display: flex; 
      justify-content: center; 
      align-items: center; 
      height: 100vh; 
    }
    .login-container { 
      background: white; 
      padding: 20px; 
      border-radius: 10px; 
      box-shadow: 0 0 10px rgba(0,0,0,0.1); 
      width: 320px; 
      text-align: center; 
    }
    .form-group { margin-bottom: 15px; }
  </style>
</head>
<body>
  <div class="login-container">
    <h2>Employee Login</h2>
    
    <!-- Display error message if any -->
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
      <div class="alert alert-danger"><%= error %></div>
    <% } %>
    
    <!-- Form to request OTP -->
    <% Boolean otpSent = (Boolean) request.getAttribute("otpSent"); %>
    <% if (otpSent == null || !otpSent) { %>
      <form action="FrontControllerServlet" method="post">
        <input type="hidden" name="action" value="sendEmployeeOTP">
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" class="form-control" name="email" placeholder="Enter your email" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">Send OTP</button>
      </form>
    <% } %>
    
    <!-- If OTP is sent, show the OTP verification form -->
    <% if (otpSent != null && otpSent) { %>
      <hr>
      <form action="FrontControllerServlet" method="post">
        <input type="hidden" name="action" value="verifyOTP">
        <!-- Email is passed as hidden field to verify OTP -->
        <input type="hidden" name="email" value="<%= request.getAttribute("email") %>">
        <div class="form-group">
          <label for="otp">Enter OTP:</label>
          <input type="text" class="form-control" name="otp" placeholder="Enter OTP" required>
        </div>
        <button type="submit" class="btn btn-success w-100">Verify OTP</button>
      </form>
    <% } %>
    
    <!-- Optionally, provide a link to resend OTP if needed -->
    <% if (otpSent != null && otpSent) { %>
      <div class="mt-3">
        <a href="FrontControllerServlet?action=sendEmployeeOTP&email=<%= request.getAttribute("email") %>" class="text-decoration-none">Resend OTP</a>
      </div>
    <% } %>
    
  </div>
</body>
</html>
