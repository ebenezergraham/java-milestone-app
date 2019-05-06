package controllers.services;
/*
ebenezergraham created on 4/26/19
*/

import DAO.DAOFactory;
import DAO.MilestoneDAO;
import DAO.ShareableLinkDAO;
import domain.model.LinkDetails;
import domain.model.Milestone;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
