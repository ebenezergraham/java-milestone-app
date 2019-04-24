package domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mongodb.lang.Nullable;

import java.util.List;

public class User {

  @SerializedName("_id")
  @Expose
  private String id;
  @SerializedName("hash")
  @Expose
  private String hash;
  @SerializedName("user_name")
  @Expose
  private String userName;
  @Nullable
  @SerializedName("projects")
  @Expose
  private List<Project> projects = null;

  /**
   * No args constructor for use in serialization
   */
  public User() {
  }

  /**
   * @param id
   * @param projects
   * @param hash
   * @param userName
   */
  public User(String id, String hash, String userName, List<Project> projects) {
    super();
    this.id = id;
    this.hash = hash;
    this.userName = userName;
    this.projects = projects;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public List<Project> getProjects() {
    return projects;
  }

  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }

  public Project getProject(String title) {
    return this.projects.stream().filter(project -> project.getTitle().equals(title)).findFirst().orElse(null);
  }

  public boolean removeProject(Project p) {
    return this.projects.remove(p);
  }
}
