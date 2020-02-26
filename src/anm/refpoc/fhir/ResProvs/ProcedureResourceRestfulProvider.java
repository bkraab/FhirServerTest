package anm.refpoc.fhir.ResProvs;

import java.util.List;

import org.hl7.fhir.r4.model.Procedure;

import anm.refpoc.fhir.services.ProcedureService;

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

public class ProcedureResourceRestfulProvider implements IResourceProvider{

	@Override
	public Class<Procedure> getResourceType() {
        return Procedure.class;
    }
	
	@Read(version=true)
    public Procedure getResourceById(@IdParam IdType theId) {
		
		ProcedureService svc = new ProcedureService();
		Procedure a = svc.findById(theId);
		return a;
    }
	
	@Create
	public MethodOutcome createProcedure(@ResourceParam Procedure theProcedure) {
		
//		if (theProcedure.getIdentifierFirstRep().isEmpty()) 
//		{				
//			throw new UnprocessableEntityException("No identifier supplied");
//		}
		
		ProcedureService svc = new ProcedureService();
	    String id = svc.createProcedure(theProcedure);
	  
	    MethodOutcome retVal = new MethodOutcome();
	    retVal.setId(new IdType("Procedure", id, "1"));
	   
	    return retVal;
				
	}
		
	@Update()
	public MethodOutcome updateProcedure(@IdParam IdType theId, @ResourceParam Procedure theProcedure,
			@ResourceParam String theRawBody,
		    @ResourceParam EncodingEnum theEncodingEnum) {
		
		
		ProcedureService svc = new ProcedureService();
	    svc.updateProcedure(theId, theProcedure);		
		MethodOutcome retVal = new MethodOutcome();						
	   	return retVal;
	}
	
	@Delete()
	public MethodOutcome deleteProcedure(@IdParam IdType theId) {
		ProcedureService svc = new ProcedureService();
		svc.deleteProcedure(theId);
		MethodOutcome retVal = new MethodOutcome();		
		return retVal;
		
	}
	
	@Search
	public List<DomainResource> getAllProcedure() {
		ProcedureService svc = new ProcedureService();
		List<DomainResource> retVal = svc.findAll();
		return retVal;				
	}
}