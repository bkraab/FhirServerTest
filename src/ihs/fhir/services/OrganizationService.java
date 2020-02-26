package ihs.fhir.services;

import java.util.List;

import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Organization;

import ihs.fhir.repos.mongo.OrganizationRepoMongo;

public class OrganizationService {

	public String createOrganization(Organization organization)
	{
		
		OrganizationRepoMongo repo = new OrganizationRepoMongo();
		String id = repo.insertCollection(organization);		
		return id;
		
	}
	
	public void updateOrganization(IdType theId, Organization organization)
	{
		OrganizationRepoMongo repo = new OrganizationRepoMongo();
		repo.updateOrganization(theId, organization);		
	}
	
	public void deleteOrganization(IdType theId)
	{
		OrganizationRepoMongo repo = new OrganizationRepoMongo();
		repo.deleteDocument2(theId.getIdPart());
	}
	
	public Organization findById(IdType id)
	{
		OrganizationRepoMongo repo = new OrganizationRepoMongo();
		Organization org = (Organization)repo.findById(id);			
		return org;		
	}
	
	public List<DomainResource> findAll()
	{
		OrganizationRepoMongo repo = new OrganizationRepoMongo();
		List<DomainResource> orgs = repo.findAll();		
		return orgs;		
	}
}
