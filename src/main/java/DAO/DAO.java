package DAO;

import com.mongodb.*;
import domain.model.User;


public class DAO {
	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
	DB database = mongoClient.getDB("mpdb");
	DBCollection collection = database.getCollection("users");
	
	public User getUser(String username){
		DBObject query = new BasicDBObject("username", username);
		DBCursor cursor = collection.find(query);
		return new User();
	}
	
	public void addUser(){
		collection.insert();
	}
}
