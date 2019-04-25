package DAO;

import domain.model.Milestone;
import domain.model.Milestone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

@SuppressWarnings("SqlDialectInspection")
public class H2Milestone implements AutoCloseable {
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

  public H2Milestone() {
    this(FILE);
  }

  public H2Milestone(String db) {
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


  public void addMilestone(Milestone ml) {
      final String ADD_MILESTONE_QUERY = "INSERT INTO milestones (title,description,status, start_date, due_date, " +
          "end_date, project_id) VALUES (?,?,?,?,?,?,?)";
    try (PreparedStatement ps = connection.prepareStatement(ADD_MILESTONE_QUERY)) {
      ps.setString(1, ml.getTitle());
      ps.setString(2, ml.getDescription());
      ps.setString(3, ml.getStatus());
      ps.setString(4, ml.getStartDate());
      ps.setString(5, ml.getDueDate());
      ps.setString(6, ml.getEndDate());
      ps.setString(7, ml.getProjectId());
      ps.execute();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Milestone getMilestone(String title) {
    final String GET_MILESTONE_QUERY = "SELECT title,description,status, start_date, due_date, end_date, " +
        "project_id  FROM milestones WHERE title='"+title+"'";
    Milestone ml = new Milestone();
    try (PreparedStatement ps = connection.prepareStatement(GET_MILESTONE_QUERY)) {
      ResultSet rs = ps.executeQuery();
      System.out.println(rs);
      if (rs.next()) {
        ml.setTitle(rs.getString("title"));
        ml.setDescription(rs.getString("description"));
        ml.setStatus(rs.getString("status"));
        ml.setStartDate(rs.getString("start_date"));
        ml.setDueDate(rs.getString("due_date"));
        ml.setEndDate(rs.getString("end_date"));
        ml.setProjectId(rs.getString("project_id"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return ml;
  }

//    public List<Project> findProjects() {
//        final String LIST_PROJECT_QUERY = "SELECT first, last, email  FROM person";
//        List<Project> out = new ArrayList<>();
//        try (PreparedStatement ps = connection.prepareStatement(LIST_PROJECT_QUERY)) {
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                out.add(new Project(rs.getString(1), rs.getString(2), rs.getString(3)));
//             }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return out;
//    }

  private void loadResource() {
    try {
      //String cmd = new Scanner(getClass().getResource(name).openStream()).useDelimiter("\\Z").next();
      String cmd = "CREATE TABLE IF NOT EXISTS milestones (" +
          "id int AUTO_INCREMENT PRIMARY KEY," +
          "title VARCHAR(255), " +
          "description VARCHAR(255), "+
          "status VARCHAR(255) ," +
          "start_date VARCHAR(255) ," +
          "due_date VARCHAR(255)," +
          "end_date VARCHAR(255)," +
          "project_id VARCHAR(255)," +
          "foreign key (project_id) references projects(id))";
      PreparedStatement ps = connection.prepareStatement(cmd);
      ps.execute();
    } catch (SQLException e) {
      System.out.println(e);
//				throw new RuntimeException(e);
    }
  }
}
