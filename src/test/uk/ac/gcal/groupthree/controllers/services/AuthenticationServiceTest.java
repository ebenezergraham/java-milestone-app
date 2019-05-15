package uk.ac.gcal.groupthree.controllers.services;

import uk.ac.gcal.groupthree.Suite.TestEnvironment;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticationServiceTest extends TestEnvironment {
	
	AuthenticationService auth = new AuthenticationService();
	
	@After
	public void after(){
		dbUtil.deleteTestData();
	}
	
	@Test
	public void login() {
		AuthenticationService auth = new AuthenticationService();
		auth.register("newuser","password1");
		boolean result = auth.login("newuser","password1");
		assertTrue(result);
	}
	
	@Test
	public void register() {
		boolean result = auth.register("testuser","testuser");
		assertTrue(result);
	}
	
	@Test
	public void shouldFailInvalidPassword() {
		boolean result = auth.validateUsernamePassword("-- --","a");
		assertFalse(result);
	}
	
	@Test
	public void shouldPassValidPassword() {
		boolean result = auth.validateUsernamePassword("hermes","12345678");
		assertTrue(result);
	}
}

