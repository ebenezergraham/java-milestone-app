package DAO;

import domain.model.Milestone;
import domain.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"SqlDialectInspection", "Duplicates"})
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
      final String ADD_MILESTONE_QUERY = "INSERT INTO milestones (title,description,status, start_date, due_date," +
          "end_date, project_title) VALUES (?,?,?,?,?,?,?)";
    try (PreparedStatement ps = connection.prepareStatement(ADD_MILESTONE_QUERY)) {
      ps.setString(1, ml.getTitle());
      ps.setString(2, ml.getDescription());
      ps.setString(3, ml.getStatus());
      ps.setString(4, ml.getStartDate());
      ps.setString(5, ml.getDueDate());
      ps.setString(6, ml.getEndDate());
      ps.setString(7, ml.getProjectTitle());
      ps.execute();
      System.out.println("H2 adding milestone");

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Milestone getMilestone(String title) {
    final String GET_MILESTONE_QUERY = "SELECT title,description,status, start_date, due_date, end_date, project_title  FROM milestones WHERE title='"+title+"'";
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
        ml.setProjectTitle(rs.getString("project_title"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return ml;
  }

  public boolean milestoneExists(String id) {
    System.out.println(id);
    final String EXISTS_QUERY = "SELECT id FROM milestones WHERE id='"+id+"' ";
    try (PreparedStatement ps = connection.prepareStatement(EXISTS_QUERY)) {
      ResultSet rs = ps.executeQuery();
      System.out.println(rs);
      return rs.next();
    } catch (SQLException e) {
      return false;
//      throw new RuntimeException(e);
    }
  }

  public boolean editMilestone(Milestone ml) {
    final String UPDATE_MILESTONE_QUERY =
        "UPDATE milestones SET title = ?, description=?, status=?, start_date=?, due_date=?, end_date=? WHERE id = ?";

//    Project project = new Project();
    try (PreparedStatement ps = connection.prepareStatement(UPDATE_MILESTONE_QUERY)) {
      ps.setString(1, ml.getTitle());
      ps.setString(2, ml.getDescription());
      ps.setString(3, ml.getStatus());
      ps.setString(4, ml.getStartDate());
      ps.setString(5, ml.getDueDate());
      ps.setString(6, ml.getEndDate());
      ps.setString(7, ml.getId());
      ps.execute();
//      ps.setString(7, ml.getProjectTitle());
      System.out.println("there is something");
      return true;
    } catch (SQLException e) {
//      return false;
      throw new RuntimeException(e);
    }
//    return false;
  }

  public List<Milestone> findMilestones(String projectID) {
    final String LIST_MILESTONE_QUERY = "SELECT id, title,description,status, start_date, due_date, end_date, project_title FROM milestones WHERE project_title='"+projectID+"'";
    List<Milestone> out = new ArrayList<>();
    try (PreparedStatement ps = connection.prepareStatement(LIST_MILESTONE_QUERY)) {
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        out.add(new Milestone(
            rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6),
            rs.getString(7),
            rs.getString(8)
        ));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return out;
  }

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
          "project_title VARCHAR(255) NOT NULL, " +
          "foreign key (project_title) references projects(title))";
      PreparedStatement ps = connection.prepareStatement(cmd);
      ps.execute();
    } catch (SQLException e) {
      System.out.println(e);
//				throw new RuntimeException(e);
    }
  }
}
