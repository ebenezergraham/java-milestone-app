package domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Project {

  @SerializedName("_id")
  @Expose
  private String id;
  @SerializedName("milestones")
  @Expose
  private List<Milestone> milestones = null;

  /**
   * No args constructor for use in serialization
   */
  public Project() {
    this.id = "Untitled Project";
  }

  /**
   * @param id
   * @param milestones
   */
  public Project(String id, List<Milestone> milestones) {
    super();
    this.id = id;
    this.milestones = milestones;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public Milestone getMilestone(String id) {
    return this.milestones.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
  }
}
