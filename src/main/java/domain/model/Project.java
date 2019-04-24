package domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Project {

  @SerializedName("_id")
  @Expose
  private String id;
  @SerializedName("title")
  @Expose
  private String title;
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
   * @param title
   * @param milestones
   */
  public Project(String id, String title,  List<Milestone> milestones) {
    super();
    this.id = id;
    this.title = title;
    this.milestones = milestones;
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

  public List<Milestone> getMilestones() {
    return milestones;
  }

  public void setMilestones(List<Milestone> milestones) {
    this.milestones = milestones;
  }

  public boolean addMilestone(Milestone milestone) {
    return this.milestones.add(milestone);
  }

  public boolean deleteMilestone(Milestone milestone) {
    return this.milestones.remove(milestone);
  }

  public Milestone getMilestone(String title) {
    return this.milestones.stream().filter(milestone -> milestone.getTitle().equals(title)).findFirst().orElse(null);
  }
}
