
package anm.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.Organization;

import anm.fhir.resmaps.OrganizationResMap;

public class OrganizationRepoMongo extends MongoBaseRepo {

	public OrganizationRepoMongo(){
		super("organizations", new OrganizationResMap());
	}
	
	public String createOrganization(Organization organization) {
		
		OrganizationResMap pm = new OrganizationResMap();
		Document orgDoc = pm.map(organization);
		return super.createResource(orgDoc);				
	}	
}


