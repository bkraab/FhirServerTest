package ihs.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;

import ihs.fhir.maps.PatientMap;

public class PatientRepoMongo extends MongoBaseRepo{
	
	private PatientMap map;
	public PatientRepoMongo()
	{
		super("patients", PatientMap.getInstance());
		map = PatientMap.getInstance();
	}
	
	public void updatePatient(IdType theId, Patient patient) {		
		Document patDoc = (Document)map.mapFromFhir(patient);				
		this.updateDocument(theId, patDoc);					
	}
}

