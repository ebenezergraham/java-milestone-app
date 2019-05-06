package Suite;/*
ebenezergraham created on 5/6/19
*/

import DAO.DAOFactory;
import utils.DBUtil;

public class TestEnvironment {
	private static final String db = "jdbc:h2:~/mptest;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE";
	public final DBUtil dbUtil = new DBUtil();
	public DAOFactory daoFactory = new DAOFactory(db);
	
	
	
}
