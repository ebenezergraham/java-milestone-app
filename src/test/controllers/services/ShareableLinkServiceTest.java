package controllers.services;

import DAO.DAOFactory;
import DAO.MilestoneDAO;
import Suite.TestEnvironment;
import domain.model.Milestone;
import domain.model.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ShareableLinkServiceTest extends TestEnvironment {
	
	ShareableLinkService shareableLinkService = new ShareableLinkService();
	String userid = "1";
	@Test
	public void generateLink() {
		String res = shareableLinkService.generateLink("123");
		assertEquals(res.length(),44);
	}
	
	@Test
	public void getMilestones() {
		Project p = new Project("Test",userid);
		projectDAO.addProject(p);
		p = projectDAO.getProject(userid,p.getTitle());
		Milestone m = new Milestone("Group Report"," Description of Group Report","false", "","",p.getId());
		milestoneDAO.addMilestone(m);
		String res = shareableLinkService.generateLink(p.getId());
		Map<String, Object> list = shareableLinkService.getMilestones(res);
		assertEquals(list.size(),1);
	}
}
