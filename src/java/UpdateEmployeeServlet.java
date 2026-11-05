import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import db.DBConnection;
public class UpdateEmployeeServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        int empId = Integer.parseInt(req.getParameter("emp_id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String designation = req.getParameter("designation");
        double salary = Double.parseDouble(req.getParameter("salary"));
        int deptId = Integer.parseInt(req.getParameter("dept_id"));
        
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE employees SET name=?, email=?, phone=?, designation=?, salary=?, dept_id=? WHERE emp_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, designation);
            pstmt.setDouble(5, salary);
            pstmt.setInt(6, deptId);
            pstmt.setInt(7, empId);
            
            pstmt.executeUpdate();
            conn.close();
            
            res.sendRedirect("ViewEmployeeServlet");
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("ViewEmployeeServlet");
        }
        out.close();
    }
}
