package DAO;

import domain.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class ProjectDAO implements AutoCloseable {
    @SuppressWarnings("unused")
    static final Logger LOG = LoggerFactory.getLogger(ProjectDAO.class);
    private Connection connection;
    
    public ProjectDAO() {
      connection = DAOFactory.getConnection();
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
      final String ADD_PROJECT_QUERY = "INSERT INTO projects (title, user_id) VALUES (?, ?)";
      try (PreparedStatement ps = connection.prepareStatement(ADD_PROJECT_QUERY)) {
        ps.setString(1, project.getTitle());
        ps.setString(2, project.getUserId());
        ps.execute();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    public Project getProject(String userId, String title) {
      final String GET_PROJECT_QUERY =
          "SELECT id, title, user_id FROM projects WHERE title='"+title+"' AND user_id='"+userId+"'";
      Project project = new Project();
      try (PreparedStatement ps = connection.prepareStatement(GET_PROJECT_QUERY)) {
        ResultSet rs = ps.executeQuery();
        System.out.println("row "+rs.getRow());
        if (rs.getRow()!=0) {
          project.setTitle(rs.getString("title"));
          project.setUserId(rs.getString("user_id"));
          project.setId(rs.getString("id"));

        }else{
          return null;
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      return project;
    }

    public List<Project> findProjects(String userId) {
        final String LIST_PROJECT_QUERY = "SELECT id, title, USER_ID FROM projects WHERE user_id='"+userId+"'";
        List<Project> out = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(LIST_PROJECT_QUERY)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out.add(new Project(rs.getString(1), rs.getString(2),rs.getString(3)));
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }
  }

