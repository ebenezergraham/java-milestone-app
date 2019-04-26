package utils;
/*
ebenezergraham created on 4/25/19
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {
	
	private static Connection connection;
	private static final String db = "jdbc:h2:~/mp";
	
	public DBUtil() {
		try {
			connection = getConnection();
			loadResource();
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		return DriverManager.getConnection(db, "", "");
	}
	
	private void loadResource() {
		try {
			String cmd = "CREATE TABLE IF NOT EXISTS users (id int AUTO_INCREMENT PRIMARY KEY, user_name VARCHAR(255) NOT NULL UNIQUE," +
					"  hash VARCHAR(255))";
			PreparedStatement ps = connection.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
//				throw new RuntimeException(e);
		}
		try {
			String cmd = "CREATE TABLE IF NOT EXISTS projects (" +
					"id int AUTO_INCREMENT PRIMARY KEY, " +
					"title VARCHAR(255), " +
					"user_id VARCHAR(255)," +
					"foreign key (user_id) references users(id)) ";
			PreparedStatement ps = connection.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
//				throw new RuntimeException(e);
		}
		
		try {
			String cmd = "CREATE TABLE IF NOT EXISTS milestones (" +
					"id int AUTO_INCREMENT PRIMARY KEY," +
					"title VARCHAR(255), " +
					"description VARCHAR(255), "+
					"status VARCHAR(255) DEFAULT false," +
					"start_date VARCHAR(255) ," +
					"due_date VARCHAR(255)," +
					"project_id int NOT NULL, " +
					"foreign key (project_id) references projects(id))";
			PreparedStatement ps = connection.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
//				throw new RuntimeException(e);
		}
	}
}
