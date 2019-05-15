package uk.ac.gcal.groupthree.DAO;

import uk.ac.gcal.groupthree.domain.model.LinkDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.gcal.groupthree.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("Duplicates")
public class ShareableLinkDAO implements AutoCloseable  ,DAO{
	@SuppressWarnings("unused")
	static final Logger LOG = LoggerFactory.getLogger(ShareableLinkDAO.class);
	private Connection connection;
	
	public ShareableLinkDAO() {
		connection = DAOFactory.getConnection(DBUtil.dburl());
	}
	
	public ShareableLinkDAO(String url) {
		connection = DAOFactory.getConnection(url);
	}
	
	public boolean store(String projectId, String link) {
		final String QUERY = "INSERT INTO links (link,project_id) VALUES (?,?)";
		try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
			ps.setString(1, link);
			ps.setString(2, projectId);
			return ps.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public LinkDetails load(String id) {
		System.out.println("To db: " + id);
		final String QUERY = "SELECT  link, project_id  FROM links WHERE link='" + id + "';";
		LinkDetails linkDetails = null;
		try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			
			if (rs.next()) {
				linkDetails = new LinkDetails(rs.getString("link"), rs.getString("project_id"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return linkDetails;
	}
	
	public LinkDetails loadByProjectId(String id) {
		System.out.println("To db: " + id);
		final String QUERY = "SELECT  link, project_id  FROM links WHERE project_id='" + id + "';";
		LinkDetails linkDetails = null;
		try (PreparedStatement ps = connection.prepareStatement(QUERY)) {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				linkDetails = new LinkDetails(rs.getString("link"), rs.getString("project_id"));
				System.out.println(linkDetails.toString());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return linkDetails;
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
}
