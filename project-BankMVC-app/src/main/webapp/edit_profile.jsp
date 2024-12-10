<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Edit Profile</h2>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">
                <strong>Error!</strong> ${errorMessage}
            </div>
        </c:if>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">
                <strong>Success!</strong> ${successMessage}
            </div>
        </c:if>

        <form action="EditProfileController" method="GET">
            <div class="form-group">
            
            <p>Leave the field Blank If Don't want to update</p>
                <label for="firstName">First Name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" >
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="lastName"  >
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email"  >
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" 
                    >
            </div>
            
            <button type="submit" class="btn btn-primary">Update Profile</button>
        </form>

        <br>
         <button class="btn btn-secondary mt-3" onclick="window.location.href='UserDashboard.jsp'">Back to Dashboard</button>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>