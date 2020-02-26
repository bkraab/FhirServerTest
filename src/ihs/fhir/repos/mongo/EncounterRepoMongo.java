package ihs.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Encounter;

import ihs.fhir.maps.EncounterMap;

public class EncounterRepoMongo extends MongoBaseRepo{
	
	private EncounterMap map;
	public EncounterRepoMongo()
	{
		super("encounters", EncounterMap.getInstance());
		map = EncounterMap.getInstance();
	}
	
	public void updateEncounter(IdType theId, Encounter encounter) {		
		Document patDoc = (Document)map.mapFromFhir(encounter);				
		this.updateDocument(theId, patDoc);					
	}
}



