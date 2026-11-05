import java.io.*;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import db.DBConnection;
import java.util.*;
public class DepartmentServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("adminSession") == null) {
            res.sendRedirect("login.jsp");
            return;
        }
        
        List deptList = new ArrayList<>();
        
        try {
            Connection conn = DBConnection.getConnection();
            // Query to get department info along with total employee count and total salary payout
            String sql = "SELECT d.dept_id, d.dept_name, COUNT(e.emp_id) AS emp_count, COALESCE(SUM(e.salary), 0) AS total_salary " +
                         "FROM department d LEFT JOIN employees e ON d.dept_id = e.dept_id GROUP BY d.dept_id, d.dept_name";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Map<String, Object> dept = new HashMap<>();
                dept.put("dept_id", rs.getInt("dept_id"));
                dept.put("dept_name", rs.getString("dept_name"));
                dept.put("emp_count", rs.getInt("emp_count"));
                dept.put("total_salary", rs.getDouble("total_salary"));
                
                deptList.add(dept);
            }
            conn.close();
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        
        req.setAttribute("deptList", deptList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("department-list.jsp");
        dispatcher.forward(req, res);
        out.close();
    }
}
