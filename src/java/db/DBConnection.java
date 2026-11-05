package db;
import java.sql.*;
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost/ems_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            System.out.println(e);
        } catch(SQLException e){
            System.out.println("Database connection failed.");
            System.out.println(e);
        }
        return con;  
    }
}
