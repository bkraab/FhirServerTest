package anm.refpoc.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;

import anm.refpoc.fhir.maps.EncounterMap;

import org.hl7.fhir.r4.model.Encounter;

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



