package anm.fhir.services;

import org.hl7.fhir.r4.model.Encounter;

import anm.fhir.repos.mongo.EncounterRepoMongo;

public class EncounterService {

	public String createEncounter(Encounter enc){
		
		EncounterRepoMongo repo = new EncounterRepoMongo();
		String id = repo.createEncounter(enc);
		return id;		
	}
}


