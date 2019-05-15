package uk.ac.gcal.groupthree.controllers.services;
/*
ebenezergraham created on 4/26/19
*/

import uk.ac.gcal.groupthree.DAO.DAOFactory;
import uk.ac.gcal.groupthree.DAO.MilestoneDAO;
import uk.ac.gcal.groupthree.domain.model.Milestone;

import java.util.List;
import java.util.stream.Collectors;

public class MilestoneService {
	
	MilestoneDAO milestoneDAO = DAOFactory.getMilestoneDAO();
	
	
	public List<Milestone> getCompletedMilestones(List<Milestone> milestones) {
		return milestones.stream().filter(milestone -> milestone.getStatus().equals("true")).collect(Collectors.toList());
	}
	
	public  List<Milestone> getPendingMilestones(List<Milestone> milestones) {
		return milestones.stream().filter(milestone -> milestone.getStatus().equals("false")).collect(Collectors.toList());
	}
}
