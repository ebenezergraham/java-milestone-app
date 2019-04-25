package DAO;

import domain.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class H2Project implements AutoCloseable {
    @SuppressWarnings("unused")
    static final Logger LOG = LoggerFactory.getLogger(H2db.class);

    public static final String MEMORY = "jdbc:h2:mem:mpdb";
    public static final String FILE = "jdbc:h2:~/mp";
    private Connection connection;

    static Connection getConnection(String db) throws SQLException, ClassNotFoundException {
      Class.forName("org.h2.Driver");
      // ensure the driver class is loaded when the DriverManager looks for an installed class. Idiom.
      return DriverManager.getConnection(db, "", "");  // default password, ok for embedded.
    }

    public H2Project() {
      this(FILE);
    }

    public H2Project(String db) {
      try {
        connection = getConnection(db);
        loadResource();
      } catch (ClassNotFoundException | SQLException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void close() {
      try {
        if (connection != null) {
          connection.close();
          connection = null;
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }


    public void addProject(Project project) {
      final String ADD_PROJECT_QUERY = "INSERT INTO projects (title, user_id) VALUES (? ?)";
      try (PreparedStatement ps = connection.prepareStatement(ADD_PROJECT_QUERY)) {
        ps.setString(1, project.getTitle());
        ps.setString(2, project.getUserId());
        ps.execute();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public Project getProject(String title) {
      final String GET_PROJECT_QUERY = "SELECT project_id title FROM projects WHERE title='"+title+"'";
      Project project = new Project();
      try (PreparedStatement ps = connection.prepareStatement(GET_PROJECT_QUERY)) {
        ResultSet rs = ps.executeQuery();
        System.out.println(rs);
        if (rs.next()) {
          project.setTitle(rs.getString("title"));
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      return project;
    }

    public List<Project> findProjects(String userID) {
        final String LIST_PROJECT_QUERY = "SELECT id, title FROM projects WHERE user_id='"+userID+"'";
        List<Project> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(LIST_PROJECT_QUERY)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out.add(new Project(rs.getString(1), rs.getString(2)));
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    private void loadResource() {
      try {
        String cmd = "CREATE TABLE IF NOT EXISTS projects (" +
            "id int AUTO_INCREMENT PRIMARY KEY, " +
            "title VARCHAR(255) UNIQUE, " +
            "user_id VARCHAR(255)," +
            "foreign key (user_id) references users(id)) ";
        PreparedStatement ps = connection.prepareStatement(cmd);
        ps.execute();
      } catch (SQLException e) {
        System.out.println(e);
//				throw new RuntimeException(e);
      }
    }
  }

