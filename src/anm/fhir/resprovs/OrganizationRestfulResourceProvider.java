
package anm.fhir.resprovs;

import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Resource;

import anm.fhir.services.OrganizationService;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class OrganizationRestfulResourceProvider implements IResourceProvider {

	@Override
	public Class<Organization> getResourceType() {
        return Organization.class;
    }
	
	@Create
	public MethodOutcome createOrganization(@ResourceParam Organization organiztion) {
		
		OrganizationService svc = new OrganizationService();
		String id = svc.createResource(organiztion);
	
		MethodOutcome retVal = new MethodOutcome();
		retVal.setId(new IdType("Organization", id, "1"));
	   	   
		return retVal;
	}
	
	@Read(version=true)
    public Resource getResourceById(@IdParam IdType theId) {
		OrganizationService svc = new OrganizationService();
		return svc.readResourceById(theId);	
    }
			
}


