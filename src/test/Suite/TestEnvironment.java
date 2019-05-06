package Suite;/*
ebenezergraham created on 5/6/19
*/

import DAO.*;
import utils.DBUtil;

public class TestEnvironment {
	private static final String db = "jdbc:h2:~/mptest;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE";
	public final DBUtil dbUtil = new DBUtil();
	protected DAOFactory daoFactory = new DAOFactory(db);
	
	protected UserDAO userDAO = (UserDAO) daoFactory.getDAO("userdao");
	protected MilestoneDAO milestoneDAO = (MilestoneDAO) daoFactory.getDAO("milestonedao");
	protected ShareableLinkDAO shareableLinkDAO = (ShareableLinkDAO) daoFactory.getDAO("linkdao");
	protected ProjectDAO projectDAO = (ProjectDAO) daoFactory.getDAO("projectdao");
	
	
}
