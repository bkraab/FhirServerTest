package anm.fhir.resprovs;

import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Practitioner;

import anm.fhir.services.PractitionerService;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class PractitionerRestfulResourceProvider implements IResourceProvider {

	@Override
	public Class<Practitioner> getResourceType() {
        return Practitioner.class;
    }
	
	@Read(version=true)
    public Practitioner getResourceById(@IdParam IdType theId) {
		Practitioner org = new Practitioner();
        org.addIdentifier();
        return org;
    }
	
	@Create
	public MethodOutcome createPractitioner(@ResourceParam Practitioner practitioner) {
		
		PractitionerService svc = new PractitionerService();
		String id = svc.createPractitioner(practitioner);
	
		MethodOutcome retVal = new MethodOutcome();
		retVal.setId(new IdType("Practitioner", id, "1"));
	   	   
		return retVal;
	}
			
}



