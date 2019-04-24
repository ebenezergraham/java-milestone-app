package domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

  @SerializedName("_id")
  @Expose
  private String id;
  @SerializedName("hash")
  @Expose
  private String hash;
  @SerializedName("salt")
  @Expose
  private String salt;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("username")
  @Expose
  private String username;
  @SerializedName("projects")
  @Expose
  private List<Project> projects = null;

  /**
   * No args constructor for use in serialization
   */
  public User() {
  }

  public User(String hash, String username) {
    super();
    this.hash = hash;
    this.username = username;
  }

  /**
   * @param id
   * @param projects
   * @param username
   * @param email
   * @param hash
   * @param salt
   */
  public User(String id, String hash, String salt, String email, String username, List<Project> projects) {
    super();
    this.id = id;
    this.hash = hash;
    this.salt = salt;
    this.email = email;
    this.username = username;
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

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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
  public boolean removeProject(Project p){
    return this.projects.remove(p);
  }
}
