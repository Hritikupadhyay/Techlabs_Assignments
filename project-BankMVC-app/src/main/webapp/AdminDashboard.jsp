<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Home</title>
    
   
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
        .card-header {
            background-color: #343a40;
            color: white;
            text-align: center;
        }
        .alert {
            text-align: center;
        }
        .btn-custom {
            margin-right: 10px;
        }
        .card-body {
            padding: 20px;
        }
        .navbar {
            border-radius: 10px;
        }
        .btn-outline-primary {
            border-radius: 25px;
            transition: all 0.3s ease;
        }
        .btn-outline-primary:hover {
            background-color: #007bff;
            color: white;
        }
        .btn-danger {
            border-radius: 25px;
            font-weight: bold;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .table-striped tbody tr:nth-child(odd) {
            background-color: #f9f9f9;
        }
        .table-bordered {
            border: 1px solid #ddd;
            border-radius: 8px;
        }
        .table-responsive {
            overflow-x: auto;
        }
        .btn-lg {
            font-size: 1.1rem;
            padding: 0.6rem 1.2rem;
            border-radius: 25px;
        }
        .btn-primary:hover, .btn-danger:hover {
            transform: translateY(-2px);
            transition: transform 0.3s ease-in-out;
        }
        .card {
            border-radius: 10px;
            border: none;
        }
        .card-header {
            border-radius: 10px 10px 0 0;
            font-weight: bold;
        }
        .alert {
            border-radius: 8px;
        }
        .form-label {
            font-weight: 500;
        }
        .form-control-lg {
            font-size: 1.1rem;
            padding: 0.75rem;
        }
        .btn-secondary:hover {
            transform: translateY(-2px);
            transition: transform 0.3s ease-in-out;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="mt-5 text-primary text-center">Admin Dashboard</h2>

        <% String username = (String) session.getAttribute("username"); %>

        <div class="text-center my-4">
            <h3 class="text-primary font-weight-bold"><%= username %></h3>
        </div>

        
        <nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm py-3">
            <a class="navbar-brand mx-auto text-primary font-weight-bold" href="#" style="font-size: 1.7rem;">Menu</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav w-100 justify-content-center mb-2 mb-lg-0">
                    <li class="nav-item mx-2">
                        <a class="btn btn-outline-primary btn-lg w-100" href="AddCustomerController">Add New Customer</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="btn btn-outline-primary btn-lg w-100" href="GenerateAccount">Add Bank Account</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="btn btn-outline-primary btn-lg w-100" href="ViewCustomerController">View Customers</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="btn btn-outline-primary btn-lg w-100" href="TransactionViewController">View Transactions</a>
                    </li>
                </ul>
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="btn btn-danger btn-lg" href="LogoutController">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>

        
        <div class="mt-4">
            <c:if test="${not empty ViewTransaction}">
                <div class="card shadow-sm">
                    <div class="card-header bg-primary text-white">
                        <h3 class="mb-0">Transactions</h3>
                    </div>
                    <div class="card-body">
                        <form class="mb-3" action="TransactionViewController" method="get">
                            <div class="row">
                                <div class="col-md-6 mb-3 mb-md-0">
                                    <input type="text" name="customerId" class="form-control form-control-lg" placeholder="Enter Customer ID">
                                </div>
                                <div class="col-md-6 d-flex justify-content-between">
                                    <button type="submit" class="btn btn-primary btn-lg w-48">Filter</button>
                                    <button type="button" class="btn btn-danger btn-lg w-48" onclick="window.location.href='AdminDashboard.jsp'">Back</button>
                                </div>
                            </div>
                        </form>
                        <c:if test="${not empty transactions}">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Transaction ID</th>
                                            <th>Account ID</th>
                                            <th>Type</th>
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
                                                <td>${transaction.getToAccount()}</td>
                                                <td>${transaction.getDate()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                        <c:if test="${empty transactions}">
                            <p class="text-center text-danger">No transactions found.</p>
                        </c:if>
                    </div>
                </div>
            </c:if>
        </div>

       
        <div class="mt-4">
            <c:if test="${not empty ViewCustomer}">
                <div class="card shadow-sm">
                    <div class="card-header bg-primary text-white">
                        <h3 class="mb-0">Customers</h3>
                    </div>
                    <div class="card-body">
                        <form class="mb-4" action="ViewCustomerController" method="get">
                            <div class="row">
                                <div class="col-md-6 mb-3 mb-md-0">
                                    <input type="text" name="customerId" class="form-control form-control-lg" placeholder="Enter Customer ID">
                                </div>
                                <div class="col-md-6 d-flex justify-content-between">
                                    <button type="submit" class="btn btn-primary btn-lg w-48">Filter</button>
                                    <button type="button" class="btn btn-danger btn-lg w-48" onclick="window.location.href='AdminDashboard.jsp'">Back</button>
                                </div>
                            </div>
                        </form>
                        <c:if test="${not empty customers}">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered">
                                    <thead class="table-light">
                                        <tr>
                                            <th>Customer ID</th>
                                            <th>First Name</th>
                                            <th>Last Name</th>
                                            <th>Email</th>
                                            <th>Account Number</th>
                                            <th>Balance</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="customer" items="${customers}">
                                            <tr>
                                                <td>${customer.getCustomerId()}</td>
                                                <td>${customer.getFirstName()}</td>
                                                <td>${customer.getLastName()}</td>
                                                <td>${customer.getEmail()}</td>
                                                <td>${customer.getAccountNumber()}</td>
                                                <td>${customer.getBalance()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                        <c:if test="${empty customers}">
                            <p class="text-center text-danger">No customers found.</p>
                        </c:if>
                    </div>
                </div>
            </c:if>
        </div>
    </div>


        <div class="mt-4">
            <c:if test="${not empty AddAccount}">
                <div class="card shadow-sm">
                    <div class="card-header bg-primary text-white">
                        <h3 class="mb-0">Add Bank Account</h3>
                    </div>
                    <div class="card-body">
                        <form action="GenerateAccount" method="GET">
                            <div class="mb-3">
                                <label for="customerId" class="form-label">Customer ID</label>
                                <input type="text" class="form-control form-control-lg" id="customerId" name="customerId" required>
                            </div>
                            <div class="d-flex justify-content-between">
                                <button type="submit" class="btn btn-primary btn-lg" name="action" value="filter">Filter</button>
                                <button type="submit" class="btn btn-success btn-lg" name="action" value="generate">Generate Account</button>
                                <a class="btn btn-secondary btn-lg" href="AdminDashboard.jsp">Cancel</a>
                            </div>
                        </form>

                        <c:if test="${not empty customers}">
                            <div class="table-responsive mt-4">
                                <table class="table table-striped table-bordered">
                                    <thead class="table-light">
                                        <tr>
                                            <th>Customer ID</th>
                                            <th>First Name</th>
                                            <th>Last Name</th>
                                            <th>Email</th>
                                            <th>Account Number</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="customer" items="${customers}">
                                            <tr>
                                                <td>${customer.getCustomerId()}</td>
                                                <td>${customer.getFirstName()}</td>
                                                <td>${customer.getLastName()}</td>
                                                <td>${customer.getEmail()}</td>
                                                <td>${customer.getAccountNumber()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>

                        <c:if test="${empty customers}">
                            <p class="text-center text-danger mt-3">No customers found.</p>
                        </c:if>

                        <c:if test="${not empty errorMessage}">
                            <p class="text-center text-danger mt-3">${errorMessage}</p>
                        </c:if>

                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success text-center mt-3">${successMessage}</div>
                        </c:if>
                    </div>
                </div>
            </c:if>
        </div>

       
        <div class="mt-4">
            <c:if test="${not empty AddCustomer}">
                <div class="card shadow-sm">
                    <div class="card-header bg-primary text-white">
                        <h3 class="mb-0">Add New Customer</h3>
                    </div>
                    <div class="card-body">
                        <form action="AddCustomerController" method="POST">
                            <div class="mb-3">
                                <label for="firstName" class="form-label">First Name</label>
                                <input type="text" class="form-control form-control-lg" id="firstName" name="firstName" required>
                            </div>
                            <div class="mb-3">
                                <label for="lastName" class="form-label">Last Name</label>
                                <input type="text" class="form-control form-control-lg" id="lastName" name="lastName" required>
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control form-control-lg" id="email" name="email" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control form-control-lg" id="password" name="password" required>
                            </div>
                            <div class="d-flex justify-content-between">
                                <button type="submit" class="btn btn-primary btn-lg">Submit</button>
                                <button type="button" class="btn btn-secondary btn-lg" onclick="window.location.href='AdminDashboard.jsp'">Cancel</button>
                            </div>
                        </form>

                        <c:if test="${not empty errorMessage}">
                            <p class="text-center text-danger mt-3">${errorMessage}</p>
                        </c:if>
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success text-center mt-3">${successMessage}</div>
                        </c:if>
                    </div>
                </div>
            </c:if>
        </div>

   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
