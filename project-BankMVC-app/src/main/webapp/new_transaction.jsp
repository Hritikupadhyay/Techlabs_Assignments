<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Transaction</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">New Transaction</h2>
        
       
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success text-center">${successMessage}</div>
        </c:if>
        
       
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger text-center">${errorMessage}</div>
        </c:if>
        
       
        <form action="TransactionController" method="GET" class="mt-4">
            <div class="mb-3">
                <label for="transactionType" class="form-label">Transaction Type</label>
                <select class="form-select" id="transactionType" name="transactionType" required>
                    <option value="" disabled selected>Select Transaction Type</option>
                    <option value="Credit">Credit</option>
                    <option value="Debit">Debit</option>
                    <option value="Transfer">Transfer</option>
                </select>
            </div>
            
            <div class="mb-3">
                <label for="accountNumber" class="form-label">Account Number</label>
                <input type="text" class="form-control" id="accountNumber" name="accountNumber" required>
            </div>
            
            <div class="mb-3">
                <label for="amount" class="form-label">Amount</label>
                <input type="number" step="0.01" class="form-control" id="amount" name="amount" required>
            </div>
            
            <div class="mb-3" id="toAccountGroup" style="display: none;">
                <label for="toAccount" class="form-label">To Account</label>
                <input type="text" class="form-control" id="toAccount" name="toAccount">
            </div>
            
            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary">Submit</button>
                <button type="button" class="btn btn-secondary" onclick="window.location.href='UserDashboard.jsp'">Back to Dashboard</button>
            </div>
        </form>
    </div>

    <script>
        
        const transactionType = document.getElementById('transactionType');
        const toAccountGroup = document.getElementById('toAccountGroup');
        
        transactionType.addEventListener('change', () => {
            if (transactionType.value === 'Transfer') {
                toAccountGroup.style.display = 'block';
            } else {
                toAccountGroup.style.display = 'none';
            }
        });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>