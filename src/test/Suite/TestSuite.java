package Suite;/*
ebenezergraham created on 5/6/19
*/

import DAO.DAOFactoryTest;
import DAO.MilestoneTest;
import DAO.ProjectTest;
import controllers.services.AuthenticationServiceTest;
import controllers.services.ShareableLinkServiceTest;
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
