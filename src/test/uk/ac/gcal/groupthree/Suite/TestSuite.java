package uk.ac.gcal.groupthree.Suite;/*
ebenezergraham created on 5/6/19
*/

import uk.ac.gcal.groupthree.DAO.DAOFactoryTest;
import uk.ac.gcal.groupthree.DAO.MilestoneTest;
import uk.ac.gcal.groupthree.DAO.ProjectTest;
import uk.ac.gcal.groupthree.controllers.services.AuthenticationServiceTest;
import uk.ac.gcal.groupthree.controllers.services.ShareableLinkServiceTest;
import org.junit.runner.RunWith;
		import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		AuthenticationServiceTest.class,
		DAOFactoryTest.class,
		ShareableLinkServiceTest.class,
		ProjectTest.class,
		MilestoneTest.class
})

public class TestSuite {
}
