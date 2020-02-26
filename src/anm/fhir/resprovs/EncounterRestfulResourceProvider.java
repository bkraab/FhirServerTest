package anm.fhir.resprovs;

import org.hl7.fhir.r4.model.IdType;

import anm.fhir.services.EncounterService;

import org.hl7.fhir.r4.model.Encounter;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class EncounterRestfulResourceProvider implements IResourceProvider {

	@Override
	public Class<Encounter> getResourceType() {
        return Encounter.class;
    }
	
	@Read(version=true)
    public Encounter getResourceById(@IdParam IdType theId) {
		Encounter enc = new Encounter();
		enc.addIdentifier();
        return enc;
    }
	
	@Create
	public MethodOutcome createEncounter(@ResourceParam Encounter encounter) {
		
		EncounterService svc = new EncounterService();
		String id = svc.createEncounter(encounter);
	
		MethodOutcome retVal = new MethodOutcome();
		retVal.setId(new IdType("Encounter", id, "1"));
	   	   
		return retVal;
	}
			
}



