package ihs.fhir.services;

import java.util.List;

import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DomainResource;

import ihs.fhir.repos.mongo.ConditionRepoMongo;

public class ConditionService {
	
	public String createCondition(Condition condition)
	{	
		ConditionRepoMongo repo = new ConditionRepoMongo();
		String id = repo.insertCollection(condition);		
		return id;		
	}
	
	public void updateCondition(IdType theId, Condition condition)
	{
		ConditionRepoMongo repo = new ConditionRepoMongo();
		repo.updateCondition(theId, condition);		
		
	}
	
	public void deleteCondition(IdType theId)
	{
		ConditionRepoMongo repo = new ConditionRepoMongo();
		repo.deleteDocument2(theId.getIdPart());		
	}
	
	public Condition findById(IdType id)
	{
		ConditionRepoMongo repo = new ConditionRepoMongo();
		Condition con = (Condition)repo.findById(id);			
		return con;
	}
	
	public List<DomainResource> findAll()
	{
		ConditionRepoMongo repo = new ConditionRepoMongo();
		List<DomainResource> ais = repo.findAll();		
		return ais;
	}
}

