package anm.refpoc.fhir.ResProvs;

import java.util.List;

import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.Patient;

import anm.refpoc.fhir.services.PatientService;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class PatientResourceRestfulProvider implements IResourceProvider {

	@Override
	public Class<Patient> getResourceType() {
        return Patient.class;
    }
	
	@Create
	public MethodOutcome createPatient(@ResourceParam Patient thePatient) {
		
		/* 
		 * First we might want to do business validation. The UnprocessableEntityException
		 * results in an HTTP 422, which is appropriate for business rule failure
		 */
		
//		if (thePatient.getIdentifierFirstRep().isEmpty()) {
//		
//		/* It is also possible to pass an OperationOutcome resource
//	     * to the UnprocessableEntityException if you want to return
//	     * a custom populated OperationOutcome. Otherwise, a simple one
//	     * is created using the string supplied below. 
//	     */
//			throw new UnprocessableEntityException("No identifier supplied");
//		}
		
		PatientService svc = new PatientService();
	    String id = svc.createPatient(thePatient);
	  
	 
	    // This method returns a MethodOutcome object which contains
	    // the ID (composed of the type Patient, the logical ID 3746, and the
	    // version ID 1)
	    
	    
	    MethodOutcome retVal = new MethodOutcome();
	    retVal.setId(new IdType("Patient", id, "1"));
	  
	   
	    // You can also add an OperationOutcome resource to return
	    // This part is optional though:
	    OperationOutcome outcome = new OperationOutcome();
	    outcome.addIssue().setDiagnostics("One minor issue detected");
	    retVal.setOperationOutcome(outcome);  
	   
	    return retVal;
	}
		
	@Update()
	public MethodOutcome updatePatient(@IdParam IdType theId, @ResourceParam Patient thePatient,
			@ResourceParam String theRawBody,
		    @ResourceParam EncodingEnum theEncodingEnum) {
		
		
		PatientService svc = new PatientService();
	    svc.updatePatient(theId, thePatient);
		
		MethodOutcome retVal = new MethodOutcome();		
		
		
	   	return retVal;
	}
	
	@Delete()
	public MethodOutcome deletePatient(@IdParam IdType theId) {
		PatientService svc = new PatientService();
		svc.deletePatient(theId);
		MethodOutcome retVal = new MethodOutcome();		
		return retVal;
		
	}
	
	@Read(version=true)
    public Patient getResourceById(@IdParam IdType theId) {
		
		PatientService svc = new PatientService();
		Patient pat = svc.findById(theId);
		
		return pat;
    }
	
	@Search
	public List<DomainResource> getAllPatients() {
		PatientService svc = new PatientService();
		List<DomainResource> retVal = svc.findAll();	   
	   return retVal;
	}
	
		
//	@Search
//    public List<Patient> getByActive(@RequiredParam(name = Patient.SP_ACTIVE) StringParam active) {
//        
//		PatientService svc = new PatientService();
//		List<Patient> pats = svc.findByIsActive(active.getValue());
//		return pats;
//		
//    }
	
//	@Search
//    public List<Patient> getByGender(@RequiredParam(name = Patient.SP_GENDER) StringParam gender) {
//        
//		PatientService svc = new PatientService();
//		List<Patient> pats = svc.findByGender(gender.getValue());
//		return pats;
//		
//    }
	
//	@Search
//    public List<Patient> getPatient(@RequiredParam(name = Patient.SP_FAMILY) StringParam theFamilyName) {
//        
//		PatientService svc = new PatientService();
//		Patient pat = svc.findById("5cf2d55c108ca76d11e023c1");
//		return Collections.singletonList(pat);
//		
//    }
//	
//	@Search
//    public List<Patient> getByAddressCity(@RequiredParam(name = Patient.SP_ADDRESS_CITY) StringParam theCity) {
//        
//		PatientService svc = new PatientService();
//		return svc.findByAddressCity(theCity.getValue());		
//		
//    }

	


}
