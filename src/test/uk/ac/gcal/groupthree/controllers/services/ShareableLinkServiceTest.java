package uk.ac.gcal.groupthree.controllers.services;


import uk.ac.gcal.groupthree.Suite.TestEnvironment;
import org.junit.After;
import org.junit.Test;

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
