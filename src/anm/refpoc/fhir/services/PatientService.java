package anm.refpoc.fhir.services;

import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;

import anm.refpoc.fhir.repos.mongo.PatientRepoMongo;

public class PatientService {
	
	public String createPatient(Patient patient)
	{	
		PatientRepoMongo repo = new PatientRepoMongo();
		String id = repo.insertCollection(patient);		
		
		if(patient.hasId() == false)
		{
			patient.setId(id);
			Document query = new Document();
			query.put("_id", id);			
			IdType idt = new IdType(id);			
			repo.updatePatient(idt, patient);												
		}
				
		return id;
	}
	
	public void updatePatient(IdType theId, Patient patient)
	{
		PatientRepoMongo repo = new PatientRepoMongo();
		repo.updatePatient(theId, patient);		
	}
	
	public void deletePatient(IdType theId)
	{
		PatientRepoMongo repo = new PatientRepoMongo();
		repo.deleteDocument2(theId.getIdPart());		
	}
	
	public Patient findById(IdType theId)
	{
		PatientRepoMongo repo = new PatientRepoMongo();
		Patient patient = (Patient)repo.findById(theId);			
		return patient;
	}
	
	public List<DomainResource> findAll()
	{
		PatientRepoMongo repo = new PatientRepoMongo();
		List<DomainResource> patients = repo.findAll();		
		return patients;
	}
	
//	public List<Patient> findByIsActive(String isActive)
//	{
//		boolean b = Boolean.parseBoolean(isActive);
//		PatientRepoMongo repo = new PatientRepoMongo();
//		List<Patient> ps = repo.findByIsActive(b);
//		return ps;
//	}
//	
//	public List<Patient> findByGender(String gender)
//	{
//		PatientRepoMongo repo = new PatientRepoMongo();
//		List<Patient> ps = repo.findByGender(gender);
//		return ps;
//	}
//	
//	public List<Patient> findByAddressCity(String city)
//	{
//		PatientRepoMongo repo = new PatientRepoMongo();
//		List<Patient> ps = repo.findByAddressCity(city);
//		
//		return ps;
//	}

}
