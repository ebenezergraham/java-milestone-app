package controllers.services;


import Suite.TestEnvironment;
import domain.model.Project;
import domain.model.User;
import org.junit.After;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class ShareableLinkServiceTest extends TestEnvironment {
	
	ShareableLinkService shareableLinkService = new ShareableLinkService();
	
	@After
	public void after(){
		dbUtil.deleteTestData();
	}
	
	@Test
	public void generateLink() {
		String res = shareableLinkService.generateLink("123");
		assertEquals(res.length(),44);
	}
}
