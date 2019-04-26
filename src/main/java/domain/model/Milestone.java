package domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Milestone {

  @SerializedName("_id")
  @Expose
  private String id;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("status")
  @Expose
  private String status;
  @SerializedName("start_date")
  @Expose
  private String startDate;
  @SerializedName("due_date")
  @Expose
  private String dueDate;
  @SerializedName("end_date")
  @Expose
  private String projectId;

  /**
   * No args constructor for use in serialization
   */
  public Milestone() {
  }

  /**
   * @param id
   * @param title
   * @param description
   * @param status
   * @param startDate
   * @param dueDate
   */
  public Milestone(String id, String title, String description, String status, String startDate, String dueDate) {
    super();
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.startDate = startDate;
    this.dueDate = dueDate;
  }

  /**
   * @param id
   * @param startDate
   * @param title
   * @param status
   * @param description
   * @param projectId
   * @param dueDate
   */
  public Milestone(String id, String title, String description, String status, String startDate, String dueDate,
                   String projectId) {
    super();
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.startDate = startDate;
    this.dueDate = dueDate;
    this.projectId = projectId;
  }

  public Milestone(String title, String description, String projectId) {
    super();
    this.title = title;
    this.description = description;
    this.projectId = projectId;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }
}
