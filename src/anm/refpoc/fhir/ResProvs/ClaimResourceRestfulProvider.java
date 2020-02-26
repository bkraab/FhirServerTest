package anm.refpoc.fhir.ResProvs;

import java.util.List;

import org.hl7.fhir.r4.model.Claim;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;

import anm.refpoc.fhir.services.ClaimService;
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

public class ClaimResourceRestfulProvider implements IResourceProvider{

	@Override
	public Class<Claim> getResourceType() {
        return Claim.class;
    }
	
	@Read(version=true)
    public Claim getResourceById(@IdParam IdType theId) {
		
		ClaimService svc = new ClaimService();
		Claim a = svc.findById(theId);
		return a;
    }
	
	@Create
	public MethodOutcome createClaim(@ResourceParam Claim theClaim) {
		
//		if (theClaim.getIdentifierFirstRep().isEmpty()) 
//		{				
//			throw new UnprocessableEntityException("No identifier supplied");
//		}
		
		ClaimService svc = new ClaimService();
	    String id = svc.createClaim(theClaim);
	  
	    MethodOutcome retVal = new MethodOutcome();
	    retVal.setId(new IdType("Claim", id, "1"));
	   
	    return retVal;
				
	}
		
	@Update()
	public MethodOutcome updateClaim(@IdParam IdType theId, @ResourceParam Claim theClaim,
			@ResourceParam String theRawBody,
		    @ResourceParam EncodingEnum theEncodingEnum) {
		
		
		ClaimService svc = new ClaimService();
	    svc.updateClaim(theId, theClaim);		
		MethodOutcome retVal = new MethodOutcome();						
	   	return retVal;
	}
	
	@Delete()
	public MethodOutcome deleteClaim(@IdParam IdType theId) {
		ClaimService svc = new ClaimService();
		svc.deleteClaim(theId);
		MethodOutcome retVal = new MethodOutcome();		
		return retVal;
		
	}
	
	@Search
	public List<DomainResource> getAllClaim() {
		ClaimService svc = new ClaimService();
		List<DomainResource> retVal = svc.findAll();
		return retVal;				
	}
}