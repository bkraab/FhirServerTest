package anm.fhir.repos.mongo;

import java.util.Arrays;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Resource;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;


public abstract class MongoBaseRepo {

	private ServerAddress mongoServer;
	private MongoCredential mongoCredential; 
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase; 
	private String collectionName;
	private IMongoMap mongoMap;
	
	public MongoBaseRepo(){
		setup();
	}
	
	protected MongoBaseRepo(String collectionName, IMongoMap map){
		this.collectionName = collectionName;
		this.mongoMap = map;
		setup();
	}
	
	private void setup() {
		mongoServer = new ServerAddress("localhost", 27017);
		mongoClient = MongoClients.create(
		        MongoClientSettings.builder()
		                .applyToClusterSettings(builder -> 
		                        builder.hosts(Arrays.asList(mongoServer)))
		                .build());
		mongoDatabase = mongoClient.getDatabase("fhirdb");
		//docCollection = mongoDatabase.getCollection(this.collectName);	
	}
	
//	private void setup(){
//		mongoServer = new ServerAddress("va33dlvmdb330.wellpoint.com", 37043);
//		mongoCredential = MongoCredential.createScramSha1Credential("fhirtest", "dvfhir", ("fhirpassword").toCharArray());
//				
//		mongoClient = MongoClients.create(
//		        MongoClientSettings.builder()
//		                .applyToClusterSettings(builder -> 
//		                        builder.hosts(Arrays.asList(mongoServer)))
//		                .credential(mongoCredential)
//		                .build());
//		mongoDatabase = mongoClient.getDatabase("dvfhir");
//	}
	
	private void teardown(){
		this.mongoClient.close();
	}
		
	private String doInsert(Document docResource, String collectionName){
		
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);			
		collection.insertOne(docResource);
		String id = docResource.getObjectId("_id").toString();
		return id;
	}
	
	private String doInsert(Document docResource){
		
		MongoCollection<Document> collection = mongoDatabase.getCollection(this.collectionName);			
		collection.insertOne(docResource);
		String id = docResource.getObjectId("_id").toString();
		return id;
	}
	
	private Document doFindByObjectId(IdType theId, String collectionName){
		
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		FindIterable<Document> docs = collection.find(Filters.eq("_id", new ObjectId(theId.getIdPart())));
		return docs.first();		
	}
	
	private Document doFindByObjectId(IdType theId){
		
		MongoCollection<Document> collection = mongoDatabase.getCollection(this.collectionName);
		FindIterable<Document> docs = collection.find(Filters.eq("_id", new ObjectId(theId.getIdPart())));
		return docs.first();		
	}
		
	public String createResource(Document docResource) {
		
		String retVal = null;
		try {
			doInsert(docResource);
			
		}catch (MongoException e) {
			e.printStackTrace();
		}
		finally{
			this.teardown();
		}
		
		return retVal;
	}
	
	public String createResource(Document docResource, String collectionName) {
		
		String retVal = null;
		try {
			doInsert(docResource, collectionName);
			
		}catch (MongoException e) {
			e.printStackTrace();
		}
		finally{
			this.teardown();
		}
		
		return retVal;
	}
	
	public Resource readById(IdType theId){
		
		Resource retVal = null;
		
		try{
			this.setup();
			Document doc = this.doFindByObjectId(theId);
			retVal = this.mongoMap.mapTofhir(doc);
									
		}catch (MongoException e) {
			e.printStackTrace();
		}
		finally{
			this.teardown();
		}
		
		return retVal;
						
	}
	
	public Document readById_old(IdType theId){
		
		Document retVal = null;
		
		try{
			this.setup();
			retVal = this.doFindByObjectId(theId);
									
		}catch (MongoException e) {
			e.printStackTrace();
		}
		finally{
			this.teardown();
		}
		
		return retVal;
						
	}
	
	public Document readById(IdType theId, String collectionName){
		
		Document retVal = null;
		
		try{
			this.setup();
			retVal = this.doFindByObjectId(theId, collectionName);
									
		}catch (MongoException e) {
			e.printStackTrace();
		}
		finally{
			this.teardown();
		}
		
		return retVal;
						
	}
}

