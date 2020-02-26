package anm.refpoc.fhir.ResProvs;

import java.util.List;

import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.IdType;

import anm.refpoc.fhir.services.EncounterService;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class EncounterResourceRestfulProvider implements IResourceProvider {

	@Override
	public Class<Encounter> getResourceType() {
        return Encounter.class;
    }
	
	@Read(version=true)
    public Encounter getResourceById(@IdParam IdType theId) {
		
		EncounterService svc = new EncounterService();
		Encounter pat = svc.findById(theId);
		
		return pat;
    }
	
	@Create
	public MethodOutcome createEncounter(@ResourceParam Encounter theEncounter) {
		
//		if (theEncounter.getIdentifierFirstRep().isEmpty()) 
//		{				
//			throw new UnprocessableEntityException("No identifier supplied");
//		}
//		
		EncounterService svc = new EncounterService();
	    String id = svc.createEncounter(theEncounter);
	  
	    MethodOutcome retVal = new MethodOutcome();
	    retVal.setId(new IdType("Encounter", id, "1"));
	   
	    return retVal;
	}
		
	@Update()
	public MethodOutcome updateEncounter(@IdParam IdType theId, @ResourceParam Encounter theEncounter,
			@ResourceParam String theRawBody,
		    @ResourceParam EncodingEnum theEncodingEnum) {
		
		
		EncounterService svc = new EncounterService();
	    svc.updateEncounter(theId, theEncounter);
		
		MethodOutcome retVal = new MethodOutcome();		
		
		
	   	return retVal;
	}
	
	@Delete()
	public MethodOutcome deleteEncounter(@IdParam IdType theId) {
		EncounterService svc = new EncounterService();
		svc.deleteEncounter(theId);
		MethodOutcome retVal = new MethodOutcome();		
		return retVal;
		
	}
	
	@Search
	public List<DomainResource> getAllEncounters() {
		EncounterService svc = new EncounterService();
		List<DomainResource> retVal = svc.findAll();	   
	   return retVal;
	}


}
