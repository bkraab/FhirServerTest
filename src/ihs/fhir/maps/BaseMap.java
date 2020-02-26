package ihs.fhir.maps;

import org.bson.Document;
import org.hl7.fhir.r4.model.DomainResource;

import ihs.fhir.repos.maps.mongo.MongoRecursion;
import ihs.fhir.repos.mongo.ResConfigRepo;

public abstract class BaseMap {

	protected ResConfig resConfig;
	
	protected abstract DomainResource instantiateResource();
	
	
	public BaseMap() {}
	
	protected BaseMap(String resName)
	{
		ResConfigRepo repo = new ResConfigRepo();
		
		ResConfig baseRes = repo.findByResName("Resource");
		ResConfig domainRes = repo.findByResName("DomainResource");
		
		resConfig = repo.findByResName(resName);
		resConfig.setBaseResources(baseRes.getResDetails(), domainRes.getResDetails());		
	}
	
	public Document mapFromFhir(DomainResource fromFhir) 
	{
		Document doc = new Document();			
		MongoRecursion recur1 = new MongoRecursion();
		recur1.convertFromFhir(this.resConfig.getResDetails(),  fromFhir,  doc);
		
		return doc;		
	}
	
	public DomainResource mapToFhir(Object fromDoc) 
	{
		DomainResource res = instantiateResource();
		
		Document doc = (Document)fromDoc;		
		MongoRecursion recur = new MongoRecursion();
		recur.convertToFhir(this.resConfig.getResDetails(), doc, res);		
		return res;		
	}	
	
	
}

