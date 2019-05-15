
package uk.ac.gcal.groupthree.DAO;

import uk.ac.gcal.groupthree.Suite.TestEnvironment;
import uk.ac.gcal.groupthree.domain.model.Project;
import uk.ac.gcal.groupthree.domain.model.User;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class DAOFactoryTest extends TestEnvironment {
	
	@After
	public void after(){
		dbUtil.deleteTestData();
	}
	
	@Test
	public void shouldAddProject() {
		User testuser = userDAO.getUser("cleopatra");
		Project p = new Project("Test",testuser.getId());
		projectDAO.addProject(p);
		Project res = projectDAO.getProject(testuser.getId());
		assertNotNull(res);
	}
	
	@Test
	public void shouldDeleteProject() {
		User testuser = userDAO.getUser("cleopatra");
		Project p = new Project("Test",testuser.getId());
		projectDAO.addProject(p);
		p = projectDAO.getProject(testuser.getId());
		projectDAO.deleteProject(p.getId());
		p = projectDAO.getProject(testuser.getId());
		assertNull(p.getId());
	}
}

