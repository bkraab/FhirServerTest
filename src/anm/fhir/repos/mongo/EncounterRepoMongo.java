package anm.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.Encounter;

import anm.fhir.resmaps.EncounterResMap;

public class EncounterRepoMongo extends MongoBaseRepo {

	public String createEncounter(Encounter encounter) {
		
		EncounterResMap pm = new EncounterResMap();
		Document orgDoc = pm.map(encounter);
		return super.createResource(orgDoc, "encounters");				
	}		
}



