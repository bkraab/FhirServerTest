package ihs.fhir.services;

import java.util.List;

import org.hl7.fhir.r4.model.Claim;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;

import ihs.fhir.repos.mongo.ClaimRepoMongo;

public class ClaimService {
	
	public String createClaim(Claim claim)
	{
			
		ClaimRepoMongo repo = new ClaimRepoMongo();
		String id = repo.insertCollection(claim);		
		return id;		
	}
	
	public void updateClaim(IdType theId, Claim claim)
	{
		ClaimRepoMongo repo = new ClaimRepoMongo();
		repo.updateClaim(theId, claim);		
		
	}
	
	public void deleteClaim(IdType theId)
	{
		ClaimRepoMongo repo = new ClaimRepoMongo();
		repo.deleteDocument2(theId.getIdPart());		
	}
	
	public Claim findById(IdType id)
	{
		ClaimRepoMongo repo = new ClaimRepoMongo();
		Claim con = (Claim)repo.findById(id);			
		return con;
	}
	
	public List<DomainResource> findAll()
	{
		ClaimRepoMongo repo = new ClaimRepoMongo();
		List<DomainResource> ais = repo.findAll();		
		return ais;
	}
}