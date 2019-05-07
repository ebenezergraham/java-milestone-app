package Suite;/*
ebenezergraham created on 5/6/19
*/

import DAO.*;
import controllers.services.AuthenticationService;
import domain.model.Milestone;
import domain.model.User;
import org.junit.After;
import utils.DBUtil;
import utils.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class TestEnvironment {
	private static final String db = "jdbc:h2:~/mptest;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE";
	public final DBUtil dbUtil = new DBUtil(db);
	
	protected UserDAO userDAO = new UserDAO(db);
	protected MilestoneDAO milestoneDAO = new MilestoneDAO(db);
	protected ShareableLinkDAO shareableLinkDAO = new ShareableLinkDAO(db);
	protected ProjectDAO projectDAO = new ProjectDAO(db);
	protected Milestone msample = new Milestone("Group Report"," Description of Group Report","false", "","","");
	protected User testuser;
	
	
	public TestEnvironment() {
		dbUtil.loadResource();
		addUser();
	}
	
	public void addUser(){
		if (userDAO.getUser("cleopatra").getId() == null) {
			try {
				String password = PasswordHash.createHash("cleopatra");
				User user = new User();
				user.setUserName("cleopatra");
				user.setHash(password);
				userDAO.addUser(user);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}
			
		}
		testuser = userDAO.getUser("cleopatra");
		
	}
}
