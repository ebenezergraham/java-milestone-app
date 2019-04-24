package DAO;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.DB;
import domain.model.Project;
import domain.model.User;


public class DAO {
	
	public DAO(){
	
	}
	private Gson mGson = new Gson();
	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	DB database = mongoClient.getDB("mpdb");
	DBCollection collection = database.getCollection("users");
	
	public User getUser(String username){
		DBObject query = new BasicDBObject("username", username);
		DBCursor cursor = collection.find(query);
		String response = cursor.one().toString();
		User user = this.mGson.fromJson(response, User.class);
		return user;
	}
	
	public void addUser(User user){
		collection.insert(toDBObject(user));
	}
	
	public static final DBObject toDBObject(User user) {
		return new BasicDBObject("_id", user.getId())
				.append("username", user.getUsername())
				.append("hash", user.getHash());
	}
	
	public void addProject(Project project){
		collection.find(new BasicDBObject("username", "hermes"));
		collection.insert(toProjectObject(project));
	}
	
	public static final DBObject toProjectObject(Project project) {
		return new BasicDBObject("title", project.getTitle());
	}
}
