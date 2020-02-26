
package anm.fhir.services;

import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Resource;

import anm.fhir.repos.mongo.OrganizationRepoMongo;

public class OrganizationService {

	public String createResource(Organization org){
		
		OrganizationRepoMongo repo = new OrganizationRepoMongo();
		String id = repo.createOrganization(org);
		return id;		
	}
	
	public Resource readResourceById(IdType theId){
		OrganizationRepoMongo repo = new OrganizationRepoMongo();
		Resource r =  repo.readById(theId);
		return r;
		
	}
}

