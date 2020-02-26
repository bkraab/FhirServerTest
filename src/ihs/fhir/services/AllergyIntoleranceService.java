package ihs.fhir.services;

import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;

import ihs.fhir.repos.mongo.AllergyIntoleranceRepoMongo;

public class AllergyIntoleranceService {
	
	public String createAllergyIntolerance(AllergyIntolerance allergy)
	{	
		AllergyIntoleranceRepoMongo repo = new AllergyIntoleranceRepoMongo();
		String id = repo.insertCollection(allergy);		
		
		if(allergy.hasId() == false)
		{
			allergy.setId(id);
			Document query = new Document();
			query.put("_id", id);			
			IdType idt = new IdType(id);			
			repo.updateAllergyIntolerance(idt, allergy);												
		}
				
		return id;
	}
	
	public void updateAllergyIntolerance(IdType theId, AllergyIntolerance allergy)
	{
		AllergyIntoleranceRepoMongo repo = new AllergyIntoleranceRepoMongo();
		repo.updateAllergyIntolerance(theId, allergy);		
	}
	
	public void deleteAllergyIntolerance(IdType theId)
	{
		AllergyIntoleranceRepoMongo repo = new AllergyIntoleranceRepoMongo();
		repo.deleteDocument2(theId.getIdPart());		
	}
	
	public AllergyIntolerance findById(IdType id)
	{
		AllergyIntoleranceRepoMongo repo = new AllergyIntoleranceRepoMongo();
		AllergyIntolerance allergy = (AllergyIntolerance)repo.findById(id);			
		return allergy;
	}
	
	public List<DomainResource> findAll()
	{
		AllergyIntoleranceRepoMongo repo = new AllergyIntoleranceRepoMongo();
		List<DomainResource> allergys = repo.findAll();		
		return allergys;
	}
	
//	public String createAllergyIntolerancen(AllergyIntolerance allerIntol)
//	{
//		AllergyIntoleranceRepoMongo repo = new AllergyIntoleranceRepoMongo();
//		String id = repo.insertCollection(allerIntol);		
//		return id;
//		
//	}
//	
//	public void updateAllergyIntolerance(IdType theId, AllergyIntolerance allerIntol)
//	{
//		AllergyIntoleranceRepoMongo repo = new AllergyIntoleranceRepoMongo();
//		repo.updateAllergyIntolerance(theId, allerIntol);		
//		
//	}
//	
//	public void deleteAllergyIntolerance(IdType theId)
//	{
//		AllergyIntoleranceRepoMongo repo = new AllergyIntoleranceRepoMongo();
//		repo.deleteDocument2(theId.getIdPart());		
//	}
//	
//	public AllergyIntolerance findById(String id)
//	{
//		AllergyIntoleranceRepoMongo repo = new AllergyIntoleranceRepoMongo();
//		AllergyIntolerance ai = (AllergyIntolerance)repo.findById(id);			
//		return ai;		
//	}
//	
//	public List<AllergyIntolerance> findAll()
//	{
//		AllergyIntoleranceRepoMongo repo = new AllergyIntoleranceRepoMongo();
//		List<AllergyIntolerance> ais = repo.findAll();		
//		return ais;				
//	}
}
