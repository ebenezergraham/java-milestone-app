package DAO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.*;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;

/*
ebenezergraham created on 4/24/19
*/

public class MongoDB {
	
	String defaultHost = "localhost";
	int port = 27017;
	String database = "mpdb";
	String collection = "users";
	String jsonFile="data.json";
	IMongodConfig mongodConfig = null;
	MongodProcess mongod;
	
	public MongoDB(){
	
	}
	
	public void start(){
		MongodStarter starter = MongodStarter.getDefaultInstance();
		try {
			mongodConfig = new MongodConfigBuilder()
					.version(Version.Main.PRODUCTION)
					.net(new Net(defaultHost, port, Network.localhostIsIPv6()))
					.build();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MongodExecutable mongodExecutable = null;
		try {
			mongodExecutable = starter.prepare(mongodConfig);
			mongod = mongodExecutable.start();
			
			MongoClient mongo = new MongoClient(defaultHost, port);
			DB db = mongo.getDB("mpdb");
			DBCollection col = db.createCollection("users", new BasicDBObject());
			col.save(new BasicDBObject("_id_","hermes"));
			
		} catch (IOException e) {
			e.printStackTrace();
		} /*finally {
			if (mongodExecutable != null)
				mongodExecutable.stop();
		}*/
		
	}
	
	public void testStartAndStopMongoImportAndMongod() throws UnknownHostException, IOException {
		
		try {
			MongoImportProcess mongoImport = startMongoImport(defaultHost, port, database,collection,jsonFile,true,true,true);
			try {
				MongoClient mongoClient = new MongoClient(defaultHost, port);
				System.out.println("DB Names: " + mongoClient.getDatabaseNames());
			} finally {
				mongoImport.stop();
			}
		} finally {
			mongod.stop();
		}
	}
	
	private MongoImportProcess startMongoImport(String bindIp, int port, String dbName, String collection, String jsonFile, Boolean jsonArray,Boolean upsert, Boolean drop)
			throws UnknownHostException, IOException {
		IMongoImportConfig mongoImportConfig = new MongoImportConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.net(new Net(bindIp, port, Network.localhostIsIPv6()))
				.db(dbName)
				.collection(collection)
				.upsert(upsert)
				.dropCollection(drop)
				.jsonArray(jsonArray)
				.importFile(jsonFile)
				.build();
		
		MongoImportExecutable mongoImportExecutable = MongoImportStarter.getDefaultInstance().prepare(mongoImportConfig);
		MongoImportProcess mongoImport = mongoImportExecutable.start();
		return mongoImport;
	}
}
