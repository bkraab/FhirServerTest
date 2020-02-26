package ihs.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.AllergyIntolerance;

import ihs.fhir.maps.AllergyIntoleranceMap;

public class AllergyIntoleranceRepoMongo extends MongoBaseRepo{
	
	private AllergyIntoleranceMap map;
	public AllergyIntoleranceRepoMongo()
	{
		super("allergyIntolerances", AllergyIntoleranceMap.getInstance());
		map = AllergyIntoleranceMap.getInstance();
	}
	
	public void updateAllergyIntolerance(IdType theId, AllergyIntolerance allergyIntolerance) {		
		Document doc = (Document)map.mapFromFhir(allergyIntolerance);				
		this.updateDocument(theId, doc);					
	}
	
	
}


