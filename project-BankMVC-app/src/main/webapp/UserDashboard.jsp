<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .navbar-nav a {
            margin-right: 15px;
        }
        .container {
            margin-top: 40px;
        }
        .navbar {
            background-color: #343a40;
        }
        .navbar-nav a {
            font-weight: 600;
        }
        .navbar-brand {
            color: #fff;
            font-size: 1.8rem;
        }
        .navbar-nav .nav-link {
            color: #fff !important;
        }
        .navbar-nav .nav-link:hover {
            color: #007bff !important;
        }
        .card-header {
            background-color: #343a40;
            color: white;
            text-align: center;
        }
        .btn-custom {
            margin-right: 10px;
        }
        .card-body {
            padding: 20px;
        }
        .btn-primary {
            font-weight: bold;
        }
        .btn-secondary {
            font-weight: bold;
            background-color: #6c757d;
            border-color: #6c757d;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }
        .table-striped tbody tr:nth-of-type(odd) {
            background-color: #f8f9fa;
        }
        .table-striped tbody tr:nth-of-type(even) {
            background-color: #ffffff;
        }
        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }
        .text-success {
            font-size: 1.5rem;
            font-weight: bold;
        }
        .text-primary {
            color: #007bff;
        }
        .btn-lg {
            font-size: 1.2rem;
        }
        .btn-lg:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container my-5">
        <h2 class="text-primary text-center my-4">Customer Dashboard</h2>

        <% 
            String username = (String) session.getAttribute("username");
        %>

        <div class="text-center my-3">
            <h3 class="text-success" style="font-size: 2rem; font-weight: 600;">Customer Email: 
                <span class="text-primary" style="font-size: 1.8rem; font-weight: 500;">
                    <%= username %>
                </span>
            </h3>
        </div>

        <nav class="navbar navbar-expand-lg navbar-light shadow-sm mb-4">
            <a class="navbar-brand mx-auto" href="#">Menu</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav w-100 text-center">
                    <li class="nav-item mx-2">
                        <a class="btn btn-primary w-100" href="PassBookController">Passbook</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="btn btn-primary w-100" href="new_transaction.jsp">New Transaction</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="btn btn-primary w-100" href="edit_profile.jsp">Edit Profile</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="btn btn-primary w-100" href="AccountViewController">View Accounts</a>
                    </li>
                </ul>
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="btn btn-danger btn-lg" href="LogoutController">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- passbook/transaction-->
        <c:if test="${not empty passbook}">
            <div class="card mb-4 shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h4>Passbook</h4>
                </div>
                <div class="card-body">
                    <form action="PassBookController" method="GET" class="mb-4">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="accountNumber" class="form-label">Account Number</label>
                                <input type="text" id="accountNumber" name="accountNumber" class="form-control" placeholder="Enter account number (optional)">
                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="transactionType" class="form-label">Transaction Type</label>
                                <select id="transactionType" name="transactionType" class="form-select">
                                    <option value="">All</option>
                                    <option value="Credit">Credit</option>
                                    <option value="Debit">Debit</option>
                                    <option value="Transfer">Transfer</option>
                                </select>
                            </div>
                            <div class="col-md-3 d-flex align-items-end">
                                <button type="submit" class="btn btn-primary w-100">Filter</button>
                            </div>
                        </div>
                    </form>

                    <c:if test="${not empty transactions}">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Transaction ID</th>
                                    <th>Account Number</th>
                                    <th>Transaction Type</th>
                                    <th>Amount</th>
                                    <th>To Account</th>
                                    <th>Date</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="transaction" items="${transactions}">
                                    <tr>
                                        <td>${transaction.getTransactionId()}</td>
                                        <td>${transaction.getAccountId()}</td>
                                        <td>${transaction.getType()}</td>
                                        <td>${transaction.getAmount()}</td>
                                        <td><c:out value="${transaction.getToAccount()}" default="N/A" /></td>
                                        <td>${transaction.getDate()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                    <c:if test="${empty transactions}">
                        <p class="text-center text-warning">No transactions found.</p>
                    </c:if>

                    <c:if test="${not empty errorMessage}">
                        <p class="text-center text-danger">${errorMessage}</p>
                    </c:if>

                    <button class="btn btn-secondary mt-3" onclick="window.location.href='UserDashboard.jsp'">Back to Dashboard</button>
                </div>
            </div>
        </c:if>

        <!-- acccnt of customer who loginned -->
        <c:if test="${not empty ViewAccounts}">
            <div class="card mb-4 shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h4>View Accounts</h4>
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Customer ID</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Account Number</th>
                                <th>Balance</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="account" items="${accounts}">
                                <tr>
                                    <td>${account.customerId}</td>
                                    <td>${account.firstName}</td>
                                    <td>${account.lastName}</td>
                                    <td>${account.accountNumber}</td>
                                    <td>${account.balance}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <c:if test="${empty accounts}">
                        <p class="text-center text-warning">No accounts found.</p>
                    </c:if>

                    <c:if test="${not empty errorMessage}">
                        <p class="text-center text-danger">${errorMessage}</p>
                    </c:if>

                    <button class="btn btn-secondary mt-3" onclick="window.location.href='UserDashboard.jsp'">Back to Dashboard</button>
                </div>
            </div>
        </c:if>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
