package anm.refpoc.fhir.services;

import java.util.List;

import org.hl7.fhir.r4.model.Practitioner;

import anm.refpoc.fhir.repos.mongo.PractitionerRepoMongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;

public class PractitionerService {

	public String createPractitioner(Practitioner practitioner)
	{
		
		PractitionerRepoMongo repo = new PractitionerRepoMongo();
		String id = repo.insertCollection(practitioner);		
		
		if(practitioner.hasId() == false)
		{
			practitioner.setId(id);
			Document query = new Document();
			query.put("_id", id);			
			IdType idt = new IdType(id);			
			repo.updatePractitioner(idt, practitioner);												
		}
		
		
		
		return id;		
	}
	
	public void updatePractitioner(IdType theId, Practitioner practitioner)
	{
		PractitionerRepoMongo repo = new PractitionerRepoMongo();
		repo.updatePractitioner(theId, practitioner);		
		
	}
	
	public void deletePractitioner(IdType theId)
	{
		PractitionerRepoMongo repo = new PractitionerRepoMongo();
		repo.deleteDocument2(theId.getIdPart());		
	}
	
	public Practitioner findById(IdType id)
	{
		PractitionerRepoMongo repo = new PractitionerRepoMongo();
		Practitioner con = (Practitioner)repo.findById(id);			
		return con;
	}
	
	public List<DomainResource> findAll()
	{
		PractitionerRepoMongo repo = new PractitionerRepoMongo();
		List<DomainResource> ais = repo.findAll();		
		return ais;
	}
}
