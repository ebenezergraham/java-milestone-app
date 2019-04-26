package domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Project {
	
	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("title")
	@Expose
	private String title;
	@SerializedName("user_id")
	@Expose
	private String userId;
	@SerializedName("milestones")
	@Expose
	private List<Milestone> milestones = null;
	
	/**
	 * No args constructor for use in serialization
	 */
	public Project() {
	}
	
	/**
	 * @param id
	 * @param milestones
	 * @param title
	 * @param userId
	 */
	public Project(String id, String title, String userId, List<Milestone> milestones) {
		super();
		this.id = id;
		this.title = title;
		this.userId = userId;
		this.milestones = milestones;
	}
	
	public Project(String id, String title, String userId) {
		super();
		this.id = id;
		this.title = title;
		this.userId = userId;
	}
	
	public Project(String title, String userId) {
		super();
		this.title = title;
		this.userId = userId;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public List<Milestone> getMilestones() {
		return milestones;
	}
	
	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}
	
	public boolean addMilestone(Milestone milestone) {
		return this.milestones.add(milestone);
	}
	
	public void deleteMilestone(Milestone milestone) {
		this.milestones.remove(milestone);
	}
	
	public Milestone getMilestone(String title) {
		return this.milestones.stream().filter(milestone -> milestone.getTitle().equals(title)).findFirst().orElse(null);
	}
	
	@Override
	public String toString() {
		return "Project{" +
				"id='" + id + '\'' +
				", title='" + title + '\'' +
				", userId='" + userId + '\'' +
				", milestones=" + milestones +
				'}';
	}
}
