
package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/bancainternet?useSSL=false&useTimezone=true&serverTimezone=UTC";
    private final String JDBC_USER = "root";
    private final String JDBC_PASSWORD = "";
    
    public Connection getConnection() {
        Connection connection = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error connection " + e.getMessage());
        }
        return connection;
    }   
    
    public void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            System.out.println("close rs " + e.getStackTrace());
        }
    }
    
    public void close(PreparedStatement ps) {
        try {
            ps.close();
        } catch (SQLException e) {
            System.out.println("close ps " + e.getStackTrace());
        }
    }
    
    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("close connection " + e.getStackTrace());
        }
    }
    
}
