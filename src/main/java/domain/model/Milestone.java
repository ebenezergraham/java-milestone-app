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
  private String endDate;
  @SerializedName("project_id")
  @Expose
  private String projectTitle;

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
   * @param endDate
   */
  public Milestone(String id, String title, String description, String status, String startDate, String dueDate, String endDate) {
    super();
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.startDate = startDate;
    this.dueDate = dueDate;
    this.endDate = endDate;
  }

  /**
   * @param id
   * @param startDate
   * @param title
   * @param status
   * @param description
   * @param endDate
   * @param projectTitle
   * @param dueDate
   */
  public Milestone(String id, String title, String description, String status, String startDate, String dueDate,
                   String endDate, String projectTitle) {
    super();
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.startDate = startDate;
    this.dueDate = dueDate;
    this.endDate = endDate;
    this.projectTitle = projectTitle;
  }
  public Milestone(String title, String description, String status, String startDate, String dueDate, String endDate) {
    super();
    this.title = title;
    this.description = description;
    this.status = status;
    this.startDate = startDate;
    this.dueDate = dueDate;
    this.endDate = endDate;
  }
  public Milestone(String title, String description, String projectTitle) {
    super();
    this.title = title;
    this.description = description;
    this.projectTitle = projectTitle;
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

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getProjectTitle() {
    return projectTitle;
  }

  public void setProjectTitle(String projectTitle) {
    this.projectTitle = projectTitle;
  }
}
