package DAO;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import domain.model.User;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;


public class DAO {
  private Gson mGson = new Gson();
  private MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
  private MongoDatabase database = mongoClient.getDatabase("mpdb");
  private MongoCollection<Document> collection = database.getCollection("users");

  public User getUser(String username) {
    Document doc = collection.find(eq("user_name", username)).first();
    assert doc != null;
    System.out.println(doc.toString());
    String response = doc.toJson();
    return (response != null) ? this.mGson.fromJson(response, User.class) : null;
  }

/*  public void updateUser(User user) {
    collection.insert(toDBObject(user));
  }*/

  public void addUser(String username, String hash) {
    collection.insertOne(toDBObject(username, hash));
  }

  private Document toDBObject(String username, String hash) {
    System.out.println("Here");
    return new Document("user_name", username).append("hash", hash);
  }

  /*private static DBObject toDBObject(User user) {
    return new BasicDBObject("user_name", user.getUserName())
        .append("hash", user.getHash());
  }*/
/*
  public void addProject(Project project) {
    collection.insertOne(toProjectObject(project));
  }

  private static DBObject toProjectObject(Project project) {
    return new BasicDBObject("title", project.getTitle());
  }*/
}
