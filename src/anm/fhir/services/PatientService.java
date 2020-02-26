package anm.fhir.services;

import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Resource;

import anm.fhir.repos.mongo.PatientRepoMongo;

public class PatientService {

	public String createPatient(Patient patient){
		
		PatientRepoMongo repo = new PatientRepoMongo();
		String id = repo.createPatient(patient);
		return id;		
	}
	
	public Resource findByid(IdType theId){
		PatientRepoMongo repo = new PatientRepoMongo();
		Resource r =  repo.readById(theId);
		return r;
		
	}
}
