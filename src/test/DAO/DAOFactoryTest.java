
package DAO;

import Suite.TestEnvironment;
import org.junit.Test;

import static org.junit.Assert.*;

public class DAOFactoryTest extends TestEnvironment {

	@Test
	public void getUserDAO() {
		assertNotNull(userDAO);
	}
	
	@Test
	public void getMilestoneDAO() {
		assertNotNull(milestoneDAO);
	}
	
	@Test
	public void getProjectDAO() {
		assertNotNull(projectDAO);
	}
	
	@Test
	public void getShareableLink() {
		assertNotNull(shareableLinkDAO);
	}
	
}

