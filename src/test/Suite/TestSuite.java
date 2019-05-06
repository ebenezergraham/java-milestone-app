package Suite;/*
ebenezergraham created on 5/6/19
*/

import DAO.DAOFactoryTest;
import controllers.services.AuthenticationServiceTest;
import org.junit.runner.RunWith;
		import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		AuthenticationServiceTest.class,
		DAOFactoryTest.class,
})

public class TestSuite {
}
