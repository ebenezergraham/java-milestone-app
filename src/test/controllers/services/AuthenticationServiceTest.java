package controllers.services;

import Suite.TestEnvironment;
import domain.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticationServiceTest extends TestEnvironment {
	
	AuthenticationService auth = new AuthenticationService();
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void login() {
		AuthenticationService auth = new AuthenticationService();
		auth.register("newuser","password");
		boolean result = auth.login("newuser","password");
		assertTrue(result);
	}
	
	@Test
	public void register() {
		boolean result = auth.register("testuser","testuser");
		assertTrue(result);
	}
}

