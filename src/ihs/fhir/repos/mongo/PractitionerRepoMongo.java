package ihs.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Practitioner;

import ihs.fhir.maps.PractitionerMap;

public class PractitionerRepoMongo extends MongoBaseRepo{
	
	private PractitionerMap map;
	public PractitionerRepoMongo()
	{
		super("practitioners", PractitionerMap.getInstance());
		map = PractitionerMap.getInstance();
	}
	
	public void updatePractitioner(IdType theId, Practitioner practitioner) {		
		Document practDoc = (Document)map.mapFromFhir(practitioner);				
		this.updateDocument(theId, practDoc);					
	}
	
}


