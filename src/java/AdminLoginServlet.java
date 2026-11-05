import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import db.DBConnection;

public class AdminLoginServlet extends HttpServlet{
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM admin WHERE username=? AND password=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, pass);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Login successful, create session
                HttpSession session = req.getSession();
                session.setAttribute("adminSession", user);
                res.sendRedirect("dashboard.jsp");
            } else {
                // Login failed, redirect back to login with error parameter
                res.sendRedirect("login.jsp?error=1");
            }
            
            conn.close();
        } catch (Exception e) {
            System.out.println("--- LOGIN ERROR ---");
            e.printStackTrace();
            res.sendRedirect("login.jsp?error=1");
        }
        out.close();
    } 
}