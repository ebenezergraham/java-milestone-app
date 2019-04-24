package DAO;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import domain.model.Project;
import domain.model.User;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;


public class DAO {
  private Gson mGson = new Gson();
  private MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
  private MongoDatabase database = mongoClient.getDatabase("mpdb");
  private MongoCollection<Document> users = database.getCollection("users");
  private MongoCollection<Document> projects = database.getCollection("projects");
  
  public User getUser(String username) {
    Document doc = users.find(eq("user_name", username)).first();
    assert doc != null;
    System.out.println(doc.toString());
    String response = doc.toJson();
    System.out.println("old response: "+response);
    response = response.replaceAll("\\{\"\\$oid\":","");
    response = response.replaceFirst("}","");
    System.out.println(response);
    return (response != null) ? this.mGson.fromJson(response, User.class) : null;
  }

/*  public void updateUser(User user) {
    users.insert(toDBObject(user));
  }*/
  
  public void addUser(String username, String hash) {
    users.insertOne(toDBObject(username, hash));
  }
  
  private Document toDBObject(String username, String hash) {
    System.out.println("Here");
    return new Document("user_name", username).append("hash", hash);
  }

  /*private static DBObject toDBObject(User user) {
    return new BasicDBObject("user_name", user.getUserName())
        .append("hash", user.getHash());
  }*/
  
  public void addProject(String username, Project project) {
    projects.insertOne(toDBProject(project));
    Document doc = users.find(eq("user_name", username)).first();
  }
  
  private Document toDBProject(Project project) {
    return new Document("title", project.getTitle());
  }
}
