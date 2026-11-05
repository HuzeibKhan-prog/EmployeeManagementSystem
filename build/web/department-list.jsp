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
    <title>Department & Salary Management - EMS</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="main-container">
        <h2>Department & Salary Overview</h2>
        
        <div class="nav-links">
            <a href="dashboard.jsp" class="btn-sm">Dashboard</a>
            <a href="ViewEmployeeServlet" class="btn-sm">View Employees</a>
        </div>
        
        <br>
        
        <table border="1" cellpadding="10" class="data-table">
            <thead>
                <tr>
                    <th>Department ID</th>
                    <th>Department Name</th>
                    <th>Total Employees</th>
                    <th>Total Salary Payout</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Map<String, Object>> list = (List) request.getAttribute("deptList");
                    if (list != null && !list.isEmpty()) {
                        for (Map<String, Object> dept : list) {
                %>
                <tr>
                    <td><%= dept.get("dept_id") %></td>
                    <td><%= dept.get("dept_name") %></td>
                    <td><%= dept.get("emp_count") %></td>
                    <td><%= dept.get("total_salary") %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" style="text-align: center;">No departments found.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>