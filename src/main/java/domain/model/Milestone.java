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
  @SerializedName("start_date")
  @Expose
  private String startDate;
  @SerializedName("end_date")
  @Expose
  private String endDate;

  /**
   * No args constructor for use in serialization
   */
  public Milestone() {
  }

  /**
   * @param id
   * @param startDate
   * @param title
   * @param description
   * @param endDate
   */
  public Milestone(String id, String title, String description, String startDate, String endDate) {
    super();
    this.id = id;
    this.title = title;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
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

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

}
