package DAO;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import controllers.services.UserService;
import de.flapdoodle.embed.mongo.*;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;

/*
ebenezergraham created on 4/24/19
*/

public class MongoDB {
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDB.class.getName());
	String defaultHost = "localhost";
	int port = 27017;
	String database = "mpdb";
	String collection = "users";
	String jsonFile="datadb.json";
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
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (mongodExecutable == null)
				mongodExecutable.stop();
		}
	}
	
	public void importData() {
			try {
				startMongoImport(defaultHost, port, database,collection,jsonFile,true,true,false);
			} catch (IOException e) {
				LOGGER.info("Failed To import");
				e.getStackTrace();
			}
	}
	
	private MongoImportProcess startMongoImport(String bindIp, int port, String dbName, String collection, String jsonFile, Boolean jsonArray,Boolean upsert, Boolean drop)
			throws IOException {
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
		return mongoImportExecutable.start();
	}
}
