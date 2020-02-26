package anm.fhir.resprovs;

import org.hl7.fhir.r4.model.IdType;

import anm.fhir.services.ConditionService;

import org.hl7.fhir.r4.model.Condition;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class ConditionRestfulResourceProvider implements IResourceProvider {

	@Override
	public Class<Condition> getResourceType() {
        return Condition.class;
    }
	
	@Read(version=true)
    public Condition getResourceById(@IdParam IdType theId) {
		Condition enc = new Condition();
		enc.addIdentifier();
        return enc;
    }
	
	@Create
	public MethodOutcome createCondition(@ResourceParam Condition condition) {
		
		ConditionService svc = new ConditionService();
		String id = svc.createCondition(condition);
	
		MethodOutcome retVal = new MethodOutcome();
		retVal.setId(new IdType("Condition", id, "1"));
	   	   
		return retVal;
	}
			
}




