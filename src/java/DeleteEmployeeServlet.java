import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import db.DBConnection;
public class DeleteEmployeeServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        int empId = Integer.parseInt(req.getParameter("id"));
        
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM employees WHERE emp_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empId);
            
            pstmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        
        res.sendRedirect("ViewEmployeeServlet");
        out.close();
    }
}
