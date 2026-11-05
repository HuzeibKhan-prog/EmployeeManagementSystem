<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
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
        <title>Employee List - Employee Management System</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div class="main-container">
            <h2>Employee Directory</h2>

            <div class="nav-links">
                <a href="dashboard.jsp" class="btn-sm">Dashboard</a>
                <a href="add-employee.jsp" class="btn-sm">Add New Employee</a>
            </div>

            <br>
            <form action="SearchEmployeeServlet" method="get" class="search-form">
                <input type="text" name="query" placeholder="Search by name or designation..." required>
                <input type="submit" value="Search" class="btn-sm">
                <a href="ViewEmployeeServlet" class="btn-sm" style="background-color: #6c757d; text-decoration: none; padding: 6px 12px; color: white;">Reset</a>
            </form>
            <br>
            
            <table border="1" cellpadding="10" class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Designation</th>
                        <th>Department</th>
                        <th>Salary</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Map<String, Object>> list = (List) request.getAttribute("employeeList");
                        if (list != null && !list.isEmpty()) {
                            for (Map<String, Object> emp : list) {
                    %>
                    <tr>
                        <td><%= emp.get("emp_id")%></td>
                        <td><%= emp.get("name")%></td>
                        <td><%= emp.get("email")%></td>
                        <td><%= emp.get("phone") != null ? emp.get("phone") : "-"%></td>
                        <td><%= emp.get("designation")%></td>
                        <td><%= emp.get("dept_name") != null ? emp.get("dept_name") : "Unassigned"%></td>
                        <td><%= emp.get("salary")%></td>
                        <td>
                            <a href="EditEmployeeServlet?id=<%= emp.get("emp_id")%>" class="action-btn edit">Edit</a>
                            <a href="DeleteEmployeeServlet?id=<%= emp.get("emp_id")%>" class="action-btn delete" onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="8" style="text-align: center;">No employees found.</td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
    </body>
</html>