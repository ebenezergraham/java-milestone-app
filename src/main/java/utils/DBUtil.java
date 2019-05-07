package utils;
/*
ebenezergraham created on 4/25/19
*/

import DAO.*;
import controllers.services.AuthenticationService;
import controllers.services.TimeService;
import controllers.servlets.ShareableLinkServlet;
import domain.model.Milestone;
import domain.model.Project;
import domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(DBUtil.class.getName());
	private static String db = "jdbc:h2:~/mp;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE";
	private static Connection connection;
	
	public DBUtil() {
		try {
			connection = getConnection(db);
			loadResource();
			// createTestData();
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public DBUtil(String db) {
		this.db = db;
		try {
			connection = getConnection(db);
			loadResource();
			// createTestData();
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static Connection getConnection(String db) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		return DriverManager.getConnection(db, "", "");
	}
	
	public void loadResource() {
		try {
			String cmd = "CREATE TABLE IF NOT EXISTS users (id int AUTO_INCREMENT PRIMARY KEY, user_name VARCHAR(255) NOT " +
					"NULL UNIQUE ," +
					"  hash VARCHAR(255))";
			PreparedStatement ps = connection.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
//				throw new RuntimeException(e);
		}
		try {
			String cmd = "CREATE TABLE IF NOT EXISTS projects (" +
					"id int AUTO_INCREMENT PRIMARY KEY, " +
					"title VARCHAR(255), " +
					"user_id int," +
					"foreign key (user_id) references users(id)) ";
			PreparedStatement ps = connection.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
//				throw new RuntimeException(e);
		}
		
		try {
			String cmd = "CREATE TABLE IF NOT EXISTS milestones (" +
					"id int AUTO_INCREMENT PRIMARY KEY," +
					"title VARCHAR(255), " +
					"description VARCHAR(255), " +
					"status VARCHAR(255) DEFAULT false," +
					"start_date VARCHAR(255) ," +
					"due_date VARCHAR(255)," +
					"project_id int NOT NULL, " +
					"foreign key (project_id) references projects(id))";
			PreparedStatement ps = connection.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
//				throw new RuntimeException(e);
		}
		
		try {
			String cmd = "CREATE TABLE IF NOT EXISTS links (" +
					"id int AUTO_INCREMENT PRIMARY KEY," +
					"link VARCHAR(255), " +
					"project_id VARCHAR(255))";
			PreparedStatement ps = connection.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
//				throw new RuntimeException(e);
		}
	}
	
	public void createTestData() {
		UserDAO dao = (UserDAO) DAOFactory.getDAO("userdao");
		List<Project> projects = new ArrayList<>();
		new AuthenticationService().register("hermes", "hermes123");
		User user = dao.getUser("hermes");
		LOGGER.info("User Created: \n{}", user.toString());
		ProjectDAO pdao = (ProjectDAO) DAOFactory.getDAO("projectdao");
		Project p = new Project("Web Dev Group 3", user.getId());
		pdao.addProject(p);
		p = pdao.getProject(user.getId(), "Web Dev Group 3");
		LOGGER.info("Project Created \n{}", p.toString());
	/*	Milestone m = new Milestone("Group Report"," Description of Group Report","false", "","",p.getId());
		MilestoneDAO mdao = (MilestoneDAO) DAOFactory.getDAO("milestonedao");
		mdao.addMilestone(m);
		m.setTitle("Implement Report");
		m.setStatus("true");
		mdao.addMilestone(m);*/
	}
	
	public static String dburl() {
		return db;
	}
	
	public void deleteTestData() {
		try {
			String cmd = "DELETE FROM MILESTONES;DELETE FROM LINKS;DELETE FROM PROJECTS;DELETE FROM Users where user_name <> 'cleopatra'; ";
			PreparedStatement ps = connection.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
