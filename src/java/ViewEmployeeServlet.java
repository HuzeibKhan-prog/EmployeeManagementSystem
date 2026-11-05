import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import db.DBConnection;
import java.util.*;

public class ViewEmployeeServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("adminSession") == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        
        List employeeList = new ArrayList<>();
        
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT e.*, d.dept_name FROM employees e LEFT JOIN department d ON e.dept_id = d.dept_id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Map<String, Object> emp = new HashMap<>();
                emp.put("emp_id", rs.getInt("emp_id"));
                emp.put("name", rs.getString("name"));
                emp.put("email", rs.getString("email"));
                emp.put("phone", rs.getString("phone"));
                emp.put("designation", rs.getString("designation"));
                emp.put("salary", rs.getDouble("salary"));
                emp.put("dept_name", rs.getString("dept_name"));
                
                employeeList.add(emp);
            }
            conn.close();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
        
        req.setAttribute("employeeList", employeeList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("employee-list.jsp");
        dispatcher.forward(req, res);
        out.close();
    }
}
