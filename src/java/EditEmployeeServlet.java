import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import db.DBConnection;
import java.util.*;
public class EditEmployeeServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("adminSession") == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        
        int empId = Integer.parseInt(req.getParameter("id"));
        Map<String, Object> employee = null;
        
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM employees WHERE emp_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                employee = new HashMap<>();
                employee.put("emp_id", rs.getInt("emp_id"));
                employee.put("name", rs.getString("name"));
                employee.put("email", rs.getString("email"));
                employee.put("phone", rs.getString("phone"));
                employee.put("designation", rs.getString("designation"));
                employee.put("salary", rs.getDouble("salary"));
                employee.put("dept_id", rs.getInt("dept_id"));
            }
            conn.close();
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
        
        req.setAttribute("employee", employee);
        RequestDispatcher dispatcher = req.getRequestDispatcher("edit-employee.jsp");
        dispatcher.forward(req, res);
        out.close();
    }
}
