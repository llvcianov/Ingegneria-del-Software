package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
	
private static Connection conn = null;
	
	private DBManager() {}
	
	public static Connection getConnection() throws SQLException {
			
			if(conn == null || conn.isClosed()) {
				String host = System.getenv().getOrDefault("DB_HOST", "localhost");
				String password = System.getenv().getOrDefault("DB_PASSWORD", "");
				conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/gestionebiglietteria", "root", password);
			}
			
			return conn;
		
	}
	
	
	public static void closeConnection() throws SQLException {
		
			if(conn != null) {
				conn.close();
			}
	}
}
