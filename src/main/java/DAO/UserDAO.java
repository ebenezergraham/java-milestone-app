package DAO;

import domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DBUtil;

import java.sql.*;

@SuppressWarnings("Duplicates")
public class UserDAO implements AutoCloseable, DAO {
	@SuppressWarnings("unused")
	static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);
	private Connection connection;
	
	public UserDAO() {
		connection = DAOFactory.getConnection(DBUtil.dburl());
	}
	
	public boolean addUser(User user){
		final String ADD_USER_QUERY = "INSERT INTO users(user_name,hash) VALUES (?,?)";
		try (PreparedStatement ps = connection.prepareStatement(ADD_USER_QUERY)) {
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getHash());
			ps.execute();
		}catch(SQLIntegrityConstraintViolationException e){
			return false;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return true;
	}
	
	public User getUser(String username) {
		final String GET_USER_QUERY = "SELECT id, user_name,hash  FROM users WHERE user_name='" + username + "';";
		User user = new User();
		try (PreparedStatement ps = connection.prepareStatement(GET_USER_QUERY)) {
			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			
			if (rs.next()) {
				user.setId(rs.getString("ID"));
				user.setHash(rs.getString("hash"));
				user.setUserName(rs.getString("user_name"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return user;
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
