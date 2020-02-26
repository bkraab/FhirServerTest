package anm.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;

import anm.fhir.resmaps.PatientResMap;

public class PatientRepoMongo extends MongoBaseRepo {

	public PatientRepoMongo(){
		super("patients", new PatientResMap());
	}
	
	public String createPatient(Patient patient) {
		
		PatientResMap pm = new PatientResMap();
		Document doc = pm.map(patient);
		return super.createResource(doc);				
	}					
}

