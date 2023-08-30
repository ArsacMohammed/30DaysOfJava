package dao;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
public class DatabaseConnection {

	
	private static final String DB_URL ="jdbc:postgresql://localhost:5432/library_management_system";
	private static final String USER ="postgres";
	private static final String PASS="Arsac@07";
			
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(DB_URL,USER,PASS);
	}
	
	 public static void main(String[] args) {
	        
	        try {
	            Connection connection = getConnection();
	            System.out.println("Connection successful");

	            connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
