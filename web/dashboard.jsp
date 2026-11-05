<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("adminSession") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Dashboard - Employee Management System</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="dashboard-container">
        <h1>Welcome, <%= session.getAttribute("adminSession") %>!</h1>
        <p>Employee Management System Dashboard</p>
        
        <div class="menu-grid">
            <a href="add-employee.jsp" class="menu-card">Add New Employee</a>
            <a href="ViewEmployeeServlet" class="menu-card">View All Employees</a>
            <a href="DepartmentServlet" class="menu-card">Department & Salary Management</a>
            <a href="LogoutServlet" class="menu-card logout-btn">Logout</a>
        </div>
    </div>
</body>
</html>
