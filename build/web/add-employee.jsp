<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.* , db.DBConnection" %>
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
    <title>Add Employee - Employee Management System</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div class="form-container">
        <h2>Add New Employee</h2>
        
        <form action="AddEmployeeServlet" method="post">
            <div class="form-group">
                <label>Full Name:</label>
                <input type="text" name="name" required>
            </div>
            <div class="form-group">
                <label>Email:</label>
                <input type="email" name="email" required>
            </div>
            <div class="form-group">
                <label>Phone:</label>
                <input type="text" name="phone">
            </div>
            <div class="form-group">
                <label>Designation:</label>
                <input type="text" name="designation" required>
            </div>
            <div class="form-group">
                <label>Salary:</label>
                <input type="number" step="0.01" name="salary" required>
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
                            while (rs.next()) {
                    %>
                                <option value="<%= rs.getInt("dept_id") %>"><%= rs.getString("dept_name") %></option>
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
                <input type="submit" value="Add Employee" class="btn">
            </div>
        </form>
        <br>
        <a href="dashboard.jsp" style="text-decoration: none">Back to Dashboard</a>
    </div>
</body>
</html>