<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .dashboard-button {
            margin: 20px;
            padding: 40px;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center my-4">Admin Dashboard</h1>
        <div class="row justify-content-center">
            <div class="col-md-3">
                <a href="admin-employeeDashboard.jsp" class="btn btn-primary dashboard-button btn-block">Manage Employees</a>
            </div>
            <div class="col-md-3">
                <a href="admin-projectDashboard.jsp" class="btn btn-success dashboard-button btn-block">Manage Projects</a>
            </div>
            <div class="col-md-3">
                <a href="admin-clientDashboard.jsp" class="btn btn-info dashboard-button btn-block">Manage Clients</a>
            </div>
          </div>
    </div>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
