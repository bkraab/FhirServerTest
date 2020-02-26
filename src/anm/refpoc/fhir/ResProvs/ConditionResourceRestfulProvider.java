package anm.refpoc.fhir.ResProvs;

import java.util.List;

import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;

import anm.refpoc.fhir.services.ConditionService;
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

public class ConditionResourceRestfulProvider implements IResourceProvider{

	@Override
	public Class<Condition> getResourceType() {
        return Condition.class;
    }
	
	@Read(version=true)
    public Condition getResourceById(@IdParam IdType theId) {
		
		ConditionService svc = new ConditionService();
		Condition a = svc.findById(theId);
		return a;
    }
	
	@Create
	public MethodOutcome createCondition(@ResourceParam Condition theCondition) {
		
//		if (theCondition.getIdentifierFirstRep().isEmpty()) 
//		{				
//			throw new UnprocessableEntityException("No identifier supplied");
//		}
		
		ConditionService svc = new ConditionService();
	    String id = svc.createCondition(theCondition);
	  
	    MethodOutcome retVal = new MethodOutcome();
	    retVal.setId(new IdType("Condition", id, "1"));
	   
	    return retVal;
				
	}
		
	@Update()
	public MethodOutcome updateCondition(@IdParam IdType theId, @ResourceParam Condition theCondition,
			@ResourceParam String theRawBody,
		    @ResourceParam EncodingEnum theEncodingEnum) {
		
		
		ConditionService svc = new ConditionService();
	    svc.updateCondition(theId, theCondition);		
		MethodOutcome retVal = new MethodOutcome();						
	   	return retVal;
	}
	
	@Delete()
	public MethodOutcome deleteCondition(@IdParam IdType theId) {
		ConditionService svc = new ConditionService();
		svc.deleteCondition(theId);
		MethodOutcome retVal = new MethodOutcome();		
		return retVal;
		
	}
	
	@Search
	public List<DomainResource> getAllCondition() {
		ConditionService svc = new ConditionService();
		List<DomainResource> retVal = svc.findAll();
		return retVal;				
	}
}
