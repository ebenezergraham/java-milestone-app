package DAO;

import org.junit.Test;

import static org.junit.Assert.*;

public class DAOFactoryTest {
	DAOFactory df = new DAOFactory();
	
	@Test
	public void getUserDAO() {
		UserDAO testDao = DAOFactory.getUserDAO();
		assertNotNull(testDao);
	}
	
	@Test
	public void getMilestoneDAO() {
		MilestoneDAO testDao = DAOFactory.getMilestoneDAO();
		assertNotNull(testDao);
	}
	
	@Test
	public void getProjectDAO() {
		ProjectDAO testDao = DAOFactory.getProjectDAO();
		assertNotNull(testDao);
	}
}
