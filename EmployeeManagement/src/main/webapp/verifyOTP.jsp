<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Verify OTP</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <style>
    body { 
      background: #f4f4f4; 
      display: flex; 
      justify-content: center; 
      align-items: center; 
      height: 100vh; 
    }
    .container { 
      background: white; 
      padding: 30px; 
      border-radius: 8px; 
      box-shadow: 0 0 10px rgba(0,0,0,0.1); 
      width: 320px; 
      text-align: center; 
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>Verify OTP</h2>
    <form action="FrontControllerServlet" method="post">
      <!-- Set the action to a common OTP verification action in the servlet -->
      <input type="hidden" name="action" value="verifyOTP">
      <!-- Pass along the email so that the servlet knows which account to verify -->
      <input type="hidden" name="email" value="<%= request.getParameter("email") %>">
      <!-- Optionally, if you want to differentiate between client and employee, you can add a userType parameter -->
      <input type="hidden" name="userType" value="<%= request.getParameter("userType") != null ? request.getParameter("userType") : "" %>">
      
      <div class="mb-3">
        <label for="otp" class="form-label">Enter OTP:</label>
        <input type="text" name="otp" class="form-control" placeholder="Enter OTP" required>
      </div>
      <button type="submit" class="btn btn-success w-100">Verify OTP</button>
    </form>
    
    <% if(request.getParameter("error") != null){ %>
      <div class="alert alert-danger mt-3">
        <%= request.getParameter("error") %>
      </div>
    <% } %>
  </div>
</body>
</html>
