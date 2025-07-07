<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(to right, #ff7e5f, #feb47b);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            text-align: center;
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }
        h1 {
            margin-bottom: 20px;
        }
        .btn {
            width: 200px;
            margin: 10px;
            font-size: 18px;
            padding: 15px;
            border-radius: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Employee Management System</h1>
        <p>Select your login type:</p>
        <a href="adminLogin.jsp" class="btn btn-danger">Admin Login</a>
        <a href="employeeLogin.jsp" class="btn btn-primary">Employee Login</a>
        <a href="clientLogin.jsp" class="btn btn-success">Client Login</a>
    </div>
</body>
</html>
