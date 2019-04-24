package domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

  @SerializedName("_id")
  @Expose
  private String id;

  @SerializedName("hash")
  @Expose
  private String hash;

  @SerializedName("username")
  @Expose
  private String username;

  /**
   * No args constructor for use in serialization
   */
  public User() {
  }

  /**
   * @param id
   * @param username
   * @param hash
   */
  public User(String id, String username,String hash) {
    super();
    this.id = id;
    this.hash = hash;
    this.username = username;
  }

  public User(String hash, String username) {
    super();
    this.hash = hash;
    this.username = username;
  }

  public User(String username) {
    this.username = username;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
