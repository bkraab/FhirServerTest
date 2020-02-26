package anm.refpoc.fhir.ResProvs;

import java.util.List;

import org.hl7.fhir.r4.model.Practitioner;

import anm.refpoc.fhir.services.PractitionerService;

import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;

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

public class PractitionerResourceRestfulProvider implements IResourceProvider{

	@Override
	public Class<Practitioner> getResourceType() {
        return Practitioner.class;
    }
	
	@Read(version=true)
    public Practitioner getResourceById(@IdParam IdType theId) {
		
		PractitionerService svc = new PractitionerService();
		Practitioner a = svc.findById(theId);
		return a;
    }
	
	@Create
	public MethodOutcome createPractitioner(@ResourceParam Practitioner thePractitioner) {
		
//		if (thePractitioner.getIdentifierFirstRep().isEmpty()) 
//		{				
//			throw new UnprocessableEntityException("No identifier supplied");
//		}
		
		PractitionerService svc = new PractitionerService();
	    String id = svc.createPractitioner(thePractitioner);
	  
	    MethodOutcome retVal = new MethodOutcome();
	    retVal.setId(new IdType("Practitioner", id, "1"));
	   
	    return retVal;
				
	}
		
	@Update()
	public MethodOutcome updatePractitioner(@IdParam IdType theId, @ResourceParam Practitioner thePractitioner,
			@ResourceParam String theRawBody,
		    @ResourceParam EncodingEnum theEncodingEnum) {
		
		
		PractitionerService svc = new PractitionerService();
	    svc.updatePractitioner(theId, thePractitioner);		
		MethodOutcome retVal = new MethodOutcome();						
	   	return retVal;
	}
	
	@Delete()
	public MethodOutcome deletePractitioner(@IdParam IdType theId) {
		PractitionerService svc = new PractitionerService();
		svc.deletePractitioner(theId);
		MethodOutcome retVal = new MethodOutcome();		
		return retVal;
		
	}
	
	@Search
	public List<DomainResource> getAllPractitioner() {
		PractitionerService svc = new PractitionerService();
		List<DomainResource> retVal = svc.findAll();
		return retVal;				
	}
}
