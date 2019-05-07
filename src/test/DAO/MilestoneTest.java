
package DAO;

import Suite.TestEnvironment;
import domain.model.Milestone;
import domain.model.Project;
import domain.model.User;
import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MilestoneTest extends TestEnvironment {
	
	@After
	public void after(){
		dbUtil.deleteTestData();
	}
	
	@Test
	public void shouldAddMilestoneToProject() {
		User testuser = userDAO.getUser("cleopatra");
		Project p = new Project("Test1",testuser.getId());
		projectDAO.addProject(p);
		List<Project> pl = projectDAO.findProjects(testuser.getId());
		msample.setProjectId(pl.get(0).getId());
		milestoneDAO.addMilestone(msample);
		boolean m =milestoneDAO.editMilestone(msample);
		assertTrue(m);
	}
}

