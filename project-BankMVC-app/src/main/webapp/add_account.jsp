<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Bank Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h2 class="mt-5">Add Bank Account</h2>
        <form action="GenerateAccount" method="POST">
            <div class="mb-3">
                <label for="customerId" class="form-label">Customer ID</label>
                <input type="text" class="form-control" id="customerId" name="customerId" required>
            </div>
            <button type="submit" class="btn btn-primary">Generate Account</button>
            <button type="button" class="btn btn-secondary" onclick="window.location.href='AdminDashboard.jsp'">Cancel</button>
        </form>
        
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>