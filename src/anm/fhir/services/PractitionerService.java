package anm.fhir.services;

import org.hl7.fhir.r4.model.Practitioner;

import anm.fhir.repos.mongo.PractitionerRepoMongo;

public class PractitionerService {

	public String createPractitioner(Practitioner pract){
		
		PractitionerRepoMongo repo = new PractitionerRepoMongo();
		String id = repo.createOrganization(pract);
		return id;		
	}
}


