<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*, db.DBConnection" %>
<%
    if (session.getAttribute("adminSession") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Map<String, Object> emp = (Map<String, Object>) request.getAttribute("employee");
    if (emp == null) {
        response.sendRedirect("ViewEmployeeServlet");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Employee - Employee Management System</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="form-container">
        <h2>Edit Employee Details</h2>
        
        <form action="UpdateEmployeeServlet" method="post">
            <input type="hidden" name="emp_id" value="<%= emp.get("emp_id") %>">
            
            <div class="form-group">
                <label>Full Name:</label>
                <input type="text" name="name" value="<%= emp.get("name") %>" required>
            </div>
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" value="<%= emp.get("email") %>" required>
            </div>
            <div class="form-group">
                <label>Phone:</label>
                <input type="text" name="phone" value="<%= emp.get("phone") != null ? emp.get("phone") : "" %>">
            </div>
            <div class="form-group">
                <label>Designation:</label>
                <input type="text" name="designation" value="<%= emp.get("designation") %>" required>
            </div>
            <div class="form-group">
                <label>Salary:</label>
                <input type="number" step="0.01" name="salary" value="<%= emp.get("salary") %>" required>
            </div>
            <div class="form-group">
                <label>Department:</label>
                <select name="dept_id" required>
                    <option value="">Select Department</option>
                    <%
                        try {
                            Connection conn = DBConnection.getConnection();
                            Statement stmt = conn.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT * FROM department");
                            int currentDeptId = (Integer) emp.get("dept_id");
                            while (rs.next()) {
                                int deptId = rs.getInt("dept_id");
                                String selected = (deptId == currentDeptId) ? "selected" : "";
                    %>
                                <option value="<%= deptId %>" <%= selected %>><%= rs.getString("dept_name") %></option>
                    <%
                            }
                            conn.close();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    %>
                </select>
            </div>
            <div class="form-group">
                <input type="submit" value="Update Employee" class="btn">
            </div>
        </form>
        <br>
        <a href="ViewEmployeeServlet">Cancel / Back to List</a>
    </div>
</body>
</html>
