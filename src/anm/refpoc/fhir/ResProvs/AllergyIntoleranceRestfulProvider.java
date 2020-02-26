package anm.refpoc.fhir.ResProvs;

import java.util.List;

import org.hl7.fhir.r4.model.IdType;

import anm.refpoc.fhir.services.AllergyIntoleranceService;

import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.DomainResource;

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

public class AllergyIntoleranceRestfulProvider  implements IResourceProvider{

	@Override
	public Class<AllergyIntolerance> getResourceType() {
        return AllergyIntolerance.class;
    }
	
	@Create
	public MethodOutcome createAllergyIntolerance(@ResourceParam AllergyIntolerance theAllergyIntol) {
		
//		if (theAllergyIntol.getIdentifierFirstRep().isEmpty()) 
//		{				
//			throw new UnprocessableEntityException("No identifier supplied");
//		}
		
		AllergyIntoleranceService svc = new AllergyIntoleranceService();
	    String id = svc.createAllergyIntolerance(theAllergyIntol);
	  
	    MethodOutcome retVal = new MethodOutcome();
	    retVal.setId(new IdType("AllergyIntolerance", id, "1"));
	   
	    return retVal;
				
	}
		
	@Update()
	public MethodOutcome updateAllergyIntolerance(@IdParam IdType theId, @ResourceParam AllergyIntolerance theAllergyIntol,
			@ResourceParam String theRawBody,
		    @ResourceParam EncodingEnum theEncodingEnum) {
		
		
		AllergyIntoleranceService svc = new AllergyIntoleranceService();
	    svc.updateAllergyIntolerance(theId, theAllergyIntol);		
		MethodOutcome retVal = new MethodOutcome();						
	   	return retVal;
	}
	
	@Delete()
	public MethodOutcome deleteAllergyIntolerance(@IdParam IdType theId) {
		AllergyIntoleranceService svc = new AllergyIntoleranceService();
		svc.deleteAllergyIntolerance(theId);
		MethodOutcome retVal = new MethodOutcome();		
		return retVal;
		
	}
	
	@Read(version=true)
    public AllergyIntolerance getResourceById(@IdParam IdType theId) {
		
		AllergyIntoleranceService svc = new AllergyIntoleranceService();
		AllergyIntolerance a = svc.findById(theId);
		return a;
    }
	
	@Search
	public List<DomainResource> getAllAllergyIntolerance() {
		AllergyIntoleranceService svc = new AllergyIntoleranceService();
		List<DomainResource> retVal = svc.findAll();
		return retVal;				
	}
}
