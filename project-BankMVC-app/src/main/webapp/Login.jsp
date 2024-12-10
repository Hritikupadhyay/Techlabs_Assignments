<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
   
  
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            background-color: #f8f9fa;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .card {
            width: 100%;
            max-width: 400px;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 10px 15px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }

        .card-header {
            text-align: center;
            font-size: 1.5rem;
            font-weight: bold;
            color: #007bff;
            margin-bottom: 20px;
        }

        .form-label {
            font-weight: 600;
        }

        .form-control, .form-select {
            border-radius: 5px;
            font-size: 1rem;
            padding: 10px;
        }

        .btn {
            border-radius: 5px;
            font-size: 1.1rem;
            padding: 12px;
        }

        .alert {
            text-align: center;
            font-weight: bold;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }

        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }

        .mt-3 {
            margin-top: 20px;
        }

      
        @media (max-width: 576px) {
            .card {
                width: 90%;
                padding: 20px;
            }
        }
        
        
        .bank-services {
            position: absolute;
            right: 0;
            top: 50%;
            transform: translateY(-50%);
            font-size: 4rem;
            font-weight: 700;
            color: #007bff;
            text-align: center;
            white-space: nowrap;
            z-index: 1;
        }
   
    </style>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h4>Techlabs Bank Login</h4>
            </div>
            <form action="LoginAllController" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <div class="mb-3">
                    <label for="role" class="form-label">Role</label>
                    <select class="form-select" id="role" name="role">
                        <option value="Admin">Admin</option>
                        <option value="Customer">Customer</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary w-100 mb-3">Login</button>
                <button type="reset" class="btn btn-secondary w-100">Cancel</button>
            </form>
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger mt-3">${errorMessage}</div>
            </c:if>
        </div>
    </div>
    
    <div class="bank-services">
       TechLabs Bank Services  
    </div>

 
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
