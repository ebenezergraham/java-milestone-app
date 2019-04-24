package DAO;

import com.google.gson.Gson;
import com.mongodb.*;
import domain.model.Project;
import domain.model.User;


public class DAO {
  private Gson mGson = new Gson();
  private MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
  private DB database = mongoClient.getDB("mpdb");
  private DBCollection collection = database.getCollection("users");

  public User getUser(String username) {
    DBObject query = new BasicDBObject("username", username);
    DBCursor cursor = collection.find(query);
    String response = null;
    if (cursor.hasNext()) {
      response = cursor.next().toString();
    }
    return (response != null) ? this.mGson.fromJson(response, User.class) : null;
  }

  public void addUser(User user) {
    collection.insert(toDBObject(user));
  }

  public static DBObject toDBObject(User user) {
    return new BasicDBObject("username", user.getUsername())
        .append("hash", user.getHash());
  }

  public void addProject(Project project) {
    collection.insert(toProjectObject(project));
  }

  public static DBObject toProjectObject(Project project) {
    return new BasicDBObject("title", project.getTitle());
  }
}
