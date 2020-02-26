package anm.fhir.resprovs;

import org.hl7.fhir.r4.model.IdType;

import anm.fhir.services.CoverageService;

import org.hl7.fhir.r4.model.Coverage;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class CoverageRestfulResourceProvider implements IResourceProvider {

	@Override
	public Class<Coverage> getResourceType() {
        return Coverage.class;
    }
	
	@Read(version=true)
    public Coverage getResourceById(@IdParam IdType theId) {
		Coverage enc = new Coverage();
		enc.addIdentifier();
        return enc;
    }
	
	@Create
	public MethodOutcome createCondition(@ResourceParam Coverage coverage) {
		
		CoverageService svc = new CoverageService();
		String id = svc.createCoverage(coverage);
	
		MethodOutcome retVal = new MethodOutcome();
		retVal.setId(new IdType("Coverage", id, "1"));
	   	   
		return retVal;
	}
			
}





