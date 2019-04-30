package DAO;

import domain.model.Milestone;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SqlDialectInspection")
public class MilestoneDAO implements AutoCloseable {
	static final Logger LOG = LoggerFactory.getLogger(MilestoneDAO.class);
	
	private Connection connection;
	
	public MilestoneDAO() {
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
	
	
	public void addMilestone(Milestone ml) {
		final String ADD_MILESTONE_QUERY = "INSERT INTO milestones (title,description,status, start_date, due_date," +
				"project_id) VALUES (?,?,?,?,?,?)";
		try (PreparedStatement ps = connection.prepareStatement(ADD_MILESTONE_QUERY)) {
			ps.setString(1, ml.getTitle());
			ps.setString(2, ml.getDescription());
			ps.setString(3, ml.getStatus());
			ps.setString(4, ml.getStartDate());
			ps.setString(5, ml.getDueDate());
			ps.setString(6, ml.getProjectId());
			ps.execute();
			System.out.println("H2 adding milestone");
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int deleteMilestone(String ml, String projectId) {
		System.out.println(ml+ " " + projectId);
		final String DEL_MILESTONE_QUERY = "DELETE FROM milestones where id = '"+ml+"' AND project_id='"+projectId+
				"'";
		try (PreparedStatement ps = connection.prepareStatement(DEL_MILESTONE_QUERY)) {
//			ps.setString(1, title);
//			ps.setString(2, projectId);
			return ps.executeUpdate();
		} catch (SQLException e) {
			return 3;
//			throw new RuntimeException(e);
//			System.out.println("gggg");
		}
//		return false;
	}
	
	public Milestone getMilestone(String title) {
		final String GET_MILESTONE_QUERY = "SELECT title,description,status, start_date, due_date, end_date, project_id  " +
				"FROM milestones WHERE title='" + title + "'";
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
				ml.setProjectId(rs.getString("project_id"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return ml;
	}
	
	public boolean milestoneExists(String id) {
		System.out.println(id);
		final String EXISTS_QUERY = "SELECT id FROM milestones WHERE id='" + id + "' ";
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
				"UPDATE milestones SET title = ?, description=?, status=?, start_date=?, due_date=? WHERE id = ?";

//    Project project = new Project();
		try (PreparedStatement ps = connection.prepareStatement(UPDATE_MILESTONE_QUERY)) {
			ps.setString(1, ml.getTitle());
			ps.setString(2, ml.getDescription());
			ps.setString(3, ml.getStatus());
			ps.setString(4, ml.getStartDate());
			ps.setString(5, ml.getDueDate());
			ps.setString(6, ml.getId());
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
		final String LIST_MILESTONE_QUERY = "SELECT id, title,description,status, start_date, due_date, project_id FROM " +
				"milestones WHERE project_id='" + projectID + "'";
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
						rs.getString(7)
				));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return out;
	}
	
	public void updateMilestone(String projectID, Milestone ml) {
		final String UPDATE_MILESTONE_QUERY = "UPDATE milestone SET title = ?, description = ?, status = ?, start_date = " +
				"?, due_date = ? WHERE project_id = ?";
		try (PreparedStatement ps = connection.prepareStatement(UPDATE_MILESTONE_QUERY)) {
			ps.setString(1, ml.getTitle());
			ps.setString(2, ml.getDescription());
			ps.setString(3, ml.getStatus());
			ps.setString(4, ml.getStartDate());
			ps.setString(5, ml.getDueDate());
			ps.setString(6, ml.getProjectId());
			ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("Duplicates")
	public List<Milestone> completedMilestones(String projectID) {
		final String LIST_MILESTONE_QUERY = "SELECT id, title,description,status, start_date, due_date, project_id FROM " +
				"milestones WHERE project_id='" + projectID + "' AND status is not true";
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
						rs.getString(7)
				));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return out;
	}
	
	
}
