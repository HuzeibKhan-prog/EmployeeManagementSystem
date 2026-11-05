import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import db.DBConnection;

public class AddEmployeeServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String designation = req.getParameter("designation");
        double salary = Double.parseDouble(req.getParameter("salary"));
        int deptId = Integer.parseInt(req.getParameter("dept_id"));
        
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO employees (name, email, phone, designation, salary, dept_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, designation);
            pstmt.setDouble(5, salary);
            pstmt.setInt(6, deptId);
            
            pstmt.executeUpdate();
            conn.close();
            
            res.sendRedirect("ViewEmployeeServlet");
        } catch (Exception e) {
            out.println(e.getMessage());
            res.sendRedirect("add-employee.jsp?error=1");
        }
        out.close();
    }
}
