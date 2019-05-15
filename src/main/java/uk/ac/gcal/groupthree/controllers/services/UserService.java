package uk.ac.gcal.groupthree.controllers.services;

import uk.ac.gcal.groupthree.DAO.UserDAO;
import uk.ac.gcal.groupthree.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class.getName());
	private static UserService instance = null;
	private UserDAO dao = new UserDAO();
	
	private UserService() {
	}
	
	public static UserService getInstance() {
		if (instance == null) instance = new UserService();
		return instance;
	}
	
	public User getUser(String username) {
		return dao.getUser(username);
	}
}
