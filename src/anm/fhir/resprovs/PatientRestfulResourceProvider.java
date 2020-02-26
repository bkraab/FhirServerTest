package anm.fhir.resprovs;

import java.util.List;
import java.util.Collections;

//import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Identifier.IdentifierUse;

import anm.fhir.services.PatientService;

import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.Delete;
//import ca.uhn.fhir.model.primitive.UriDt;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class PatientRestfulResourceProvider implements IResourceProvider {

	@Override
	public Class<Patient> getResourceType() {
        return Patient.class;
    }
	
	@Create
	public MethodOutcome createResource(@ResourceParam Patient patient) {
	 
	  /* 
	   * First we might want to do business validation. The UnprocessableEntityException
	   * results in an HTTP 422, which is appropriate for business rule failure
	   */
//	  if (thePatient.getIdentifierFirstRep().isEmpty()) {
//	    /* It is also possible to pass an OperationOutcome resource
//	     * to the UnprocessableEntityException if you want to return
//	     * a custom populated OperationOutcome. Otherwise, a simple one
//	     * is created using the string supplied below. 
//	     */
//	    throw new UnprocessableEntityException("No identifier supplied");
//	  }
	     
		PatientService svc = new PatientService();
		String id = svc.createPatient(patient);
	
	
	
//	  AtmDao dao = new AtmDao();
//	  dao.CreatePatient(thePatient);
	  
	  // Save this patient to the database...
//	  savePatientToDatabase(thePatient);
	 
	  // This method returns a MethodOutcome object which contains
	  // the ID (composed of the type Patient, the logical ID 3746, and the
	  // version ID 1)
	  MethodOutcome retVal = new MethodOutcome();
	  retVal.setId(new IdType("Patient", id, "1"));
	   
	  // You can also add an OperationOutcome resource to return
	  // This part is optional though:
	  //OperationOutcome outcome = new OperationOutcome();
	  //outcome.addIssue().setDiagnostics("One minor issue detected");
	  //retVal.setOperationOutcome(outcome);  
	   
	  return retVal;
	}
	
	@Read(version=true)
    public Resource readResourceById(@IdParam IdType theId) {
        
		PatientService svc = new PatientService();
		Resource r = svc.findByid(theId);
		return r;
    }
	
	
	@Update()
	public MethodOutcome updatePatient(@IdParam IdType theId, @ResourceParam Patient thePatient,
			@ResourceParam String theRawBody,
		    @ResourceParam EncodingEnum theEncodingEnum) {
		System.out.println("Encoding is" + theEncodingEnum);
		System.out.println(theRawBody);
		
		
		
		MethodOutcome retVal = new MethodOutcome();
		OperationOutcome outcome = new OperationOutcome();
		outcome.addIssue().setDiagnostics("One minor issue detected");
		retVal.setOperationOutcome(outcome);
	   	return retVal;
	}
	
	@Delete()
	public void deletePatient(@IdParam IdType theId) {
	    
		System.out.println("Deelte called for ID: " + theId);
	    // otherwise, delete was successful
	    return; // can also return MethodOutcome
	}
	
	@Search
    public List<Patient> getPatient(@RequiredParam(name = Patient.SP_FAMILY) StringParam theFamilyName) {
        Patient patient = new Patient();
        patient.addIdentifier();
        patient.getIdentifier().get(0).setUse(IdentifierUse.OFFICIAL);
        patient.getIdentifier().get(0).setSystem("urn:hapitest:mrns");
        patient.getIdentifier().get(0).setValue("00001");
        patient.addName();
        patient.getName().get(0).setFamily(theFamilyName.getValue());
        patient.getName().get(0).addGiven("PatientOne");
        patient.setGender(Enumerations.AdministrativeGender.MALE);
        return Collections.singletonList(patient);
    }

}

