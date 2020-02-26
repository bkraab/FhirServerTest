package ihs.fhir.repos.mongo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;

import ihs.fhir.maps.BaseMap;

public class MongoBaseRepo {

	private String collectName;
	protected BaseMap myMap;
	
	private ServerAddress mongoServer;
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCollection <Document> docCollection; 
	
	protected MongoBaseRepo() {
		
	}
	
	protected MongoBaseRepo(String collectionName, BaseMap map) {
		this.collectName = collectionName;
		this.myMap = map;
	}		
	
	private void setup() {
		
		mongoServer = new ServerAddress("localhost", 27017);
		mongoClient = MongoClients.create(
		        MongoClientSettings.builder()
		                .applyToClusterSettings(builder -> 
		                        builder.hosts(Arrays.asList(mongoServer)))
		                .build());
		mongoDatabase = mongoClient.getDatabase("fhirdb");
		docCollection = mongoDatabase.getCollection(this.collectName);	
		
		
		
//		mongoClient = MongoClients.create("mongodb://localhost:27017");
//		mongoDatabase = mongoClient.getDatabase("fhirdb");		
//		docCollection = mongoDatabase.getCollection(this.collectName);		
	}
	
	private void teardown() {
		mongoClient.close();			
	}
	
	private List<DomainResource> doFind(Document query) {
		List<DomainResource> retVal = new ArrayList<DomainResource>(0);
		
		FindIterable<Document>  docs = this.docCollection.find(query);
			
		if(docs.first() == null) 
			return null;
		
		for(Document d: docs) {
			DomainResource drp = this.myMap.mapToFhir(d);
			retVal.add(drp);			
		}
			
		
		return retVal;		
	}
	
	private DomainResource readByObjectId(IdType theId) {
		//Document query = new Document();
		//query.put("_id", new ObjectId(id));			
		FindIterable<Document>  docs = this.docCollection.find(Filters.eq("_id", new ObjectId(theId.getIdPart())));
			
		if(docs.first() == null) 
			return null;
			
		DomainResource p = this.myMap.mapToFhir(docs.first());
		return p;
	}
				
	private String doInsert(DomainResource res)	{
		Document newDoc = (Document)this.myMap.mapFromFhir(res);
		docCollection.insertOne(newDoc);
		String id = newDoc.getObjectId("_id").toString();						
		return id;				
	}
	
	private void doUpdate(IdType theId, Document obj) {
		docCollection.replaceOne(Filters.eq("_id", new ObjectId(theId.getIdPart())), obj);														
	}
		
	private void doDelete(String oid) {		
		Document query = new Document();
		query.put("_id", new ObjectId(oid));
		docCollection.deleteOne(query);							
	}
		
	public DomainResource findById(IdType theId) {		
		try{
			setup();
			DomainResource dr = this.readByObjectId(theId);
			return dr;			
		}		
		catch (MongoException e){
			e.printStackTrace();
		}
		finally {
			teardown();
		}		
		return null;	
	}
			
	protected void updateDocument(IdType theId, Document obj) {
		try{
			setup();
			this.doUpdate(theId, obj);
		}
		catch (MongoException e){
			e.printStackTrace();
		}
		finally{
			teardown();
		}			
	}
	
	public String insertCollection(DomainResource res) {					
		try{
			setup();
			String id = doInsert(res);
			return id;
		}
		catch (MongoException e){
			e.printStackTrace();
		}
		finally{
			teardown();
		}			
		return null;			
	}	
			
	public void deleteDocument2(String oid) {
		try{
			setup();
			doDelete(oid);			
		}		
		catch (MongoException e){
			e.printStackTrace();
		}	
		finally{
			teardown();
		}
	}
	
	public List<DomainResource> findAll()
	{
		List<DomainResource> ret = null;
		try 
		{
			setup();
			Document query = new Document();
			ret = this.doFind(query);
		}
		catch (MongoException e) 
		{
			e.printStackTrace();
		}
		finally {
			teardown();
		}
		
		return ret;
				
	}	

	protected Document readInstance(String collectionName, Document query){
		Document bdo = new Document();
		
		try {
			this.setup();
			MongoCollection<Document> coll = this.mongoDatabase.getCollection(collectionName);
			FindIterable<Document>  docs = coll.find(query);
			
			return docs.first();
			
		} catch (MongoException e) {
			e.printStackTrace();
		}	
		finally {
			this.teardown();
		}
		
		return bdo;			
	}	

	protected List<Document> readAllSort(String collectionName, Document query, Document sort)
	{
		List<Document> ret = new ArrayList<Document>(0); 
		
		try {
			this.setup();
			MongoCollection<Document> coll = this.mongoDatabase.getCollection(collectionName);
			FindIterable<Document>  docs = coll.find(query).sort(sort);
			
			for(Document doc: docs) {
				ret.add(doc);
			}
																
		} catch (MongoException e) {
			e.printStackTrace();
		}	
		
		return ret;
				
	}	

}
