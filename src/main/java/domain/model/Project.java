package domain.model;

import java.util.List;

public class Project {
	private String userId;
	private String name;
	private List<Milestone> milestoneList;
	
	public Project(String userId, String name, List<Milestone> milestoneList) {
		this.userId = userId;
		this.name = name;
		this.milestoneList = milestoneList;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Milestone> getMilestoneList() {
		return milestoneList;
	}
	
	public void setMilestoneList(List<Milestone> milestoneList) {
		this.milestoneList = milestoneList;
	}
}
