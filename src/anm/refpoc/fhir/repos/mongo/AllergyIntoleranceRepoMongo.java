package anm.refpoc.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;

import anm.refpoc.fhir.maps.AllergyIntoleranceMap;

import org.hl7.fhir.r4.model.AllergyIntolerance;

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


