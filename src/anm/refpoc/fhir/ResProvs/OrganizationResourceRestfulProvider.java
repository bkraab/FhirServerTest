package anm.refpoc.fhir.ResProvs;

import java.util.List;

import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Organization;

import anm.refpoc.fhir.services.OrganizationService;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.Delete;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class OrganizationResourceRestfulProvider implements IResourceProvider{

	@Override
	public Class<Organization> getResourceType() {
        return Organization.class;
    }
	
	@Read(version=true)
    public Organization getResourceById(@IdParam IdType theId) {
		
		OrganizationService svc = new OrganizationService();
		Organization org = svc.findById(theId);
		return org;
    }
	
	@Create
	public MethodOutcome createOrganization(@ResourceParam Organization theOrganization) {
		
//		if (theOrganization.getIdentifierFirstRep().isEmpty()) 
//		{				
//			throw new UnprocessableEntityException("No identifier supplied");
//		}
		
		OrganizationService svc = new OrganizationService();
	    String id = svc.createOrganization(theOrganization);
	  
	    MethodOutcome retVal = new MethodOutcome();
	    retVal.setId(new IdType("Organization", id, "1"));
	   
	    return retVal;
				
	}
		
	@Update()
	public MethodOutcome updateOrganization(@IdParam IdType theId, @ResourceParam Organization theOrganization,
			@ResourceParam String theRawBody,
		    @ResourceParam EncodingEnum theEncodingEnum) {
		
		
		OrganizationService svc = new OrganizationService();
	    svc.updateOrganization(theId, theOrganization);
		
		MethodOutcome retVal = new MethodOutcome();		
				
	   	return retVal;
	}
	
	@Delete()
	public MethodOutcome deleteOrganization(@IdParam IdType theId) {
		OrganizationService svc = new OrganizationService();
		svc.deleteOrganization(theId);
		MethodOutcome retVal = new MethodOutcome();		
		return retVal;
		
	}
	
	@Search
	public List<DomainResource> getAllOrganization() {
		OrganizationService svc = new OrganizationService();
		List<DomainResource> retVal = svc.findAll();
		return retVal;		
	}
}
