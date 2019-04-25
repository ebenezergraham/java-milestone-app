package controllers.services;

import DAO.DAOFactory;
import domain.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticationServiceTest {
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void login() {
		AuthenticationService auth = new AuthenticationService();
		User user = new User();
		user.setUserName("testuser");
		user.setHash("password");
		DAOFactory.getUserDAO().addUser(user);
		boolean result = auth.login("testuser","password");
		assertTrue(result);
	}
	
	@Test
	public void register() {
		AuthenticationService auth = new AuthenticationService();
		boolean result = auth.register("testuser","testuser");
		assertTrue(result);
	}
}
