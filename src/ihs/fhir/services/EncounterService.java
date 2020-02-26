package ihs.fhir.services;

import java.util.List;

import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.IdType;

import ihs.fhir.repos.mongo.EncounterRepoMongo;


public class EncounterService {

	
	public String createEncounter(Encounter encounter)
	{
		EncounterRepoMongo repo = new EncounterRepoMongo();
		String id = repo.insertCollection(encounter);
		
		return id;
	}
	
	public void updateEncounter(IdType theId, Encounter encounter)
	{
		EncounterRepoMongo repo = new EncounterRepoMongo();
		repo.updateEncounter(theId, encounter);		
	}
	
	public void deleteEncounter(IdType theId)
	{
		EncounterRepoMongo repo = new EncounterRepoMongo();
		repo.deleteDocument2(theId.getIdPart());		
	}
	
	public List<DomainResource> findAll()
	{
		EncounterRepoMongo repo = new EncounterRepoMongo();
		List<DomainResource> res = repo.findAll();		
		return res;
	}
	
	public Encounter findById(IdType id)
	{
		EncounterRepoMongo repo = new EncounterRepoMongo();
		Encounter encounter = (Encounter)repo.findById(id);			
		return encounter;
	}
	
	
}
