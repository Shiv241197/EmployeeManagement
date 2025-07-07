<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGOUT</title>
</head>
<body>
%
    session.invalidate(); // Destroy session
    response.sendRedirect("employeeLogin.jsp"); // Redirect to login page
%>
</body>
</html>