package anm.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.Practitioner;

import anm.fhir.resmaps.PractitionerResMap;

public class PractitionerRepoMongo extends MongoBaseRepo {

	public String createOrganization(Practitioner practitioner) {
		
		PractitionerResMap pm = new PractitionerResMap();
		Document orgDoc = pm.map(practitioner);
		return super.createResource(orgDoc, "practitioners");				
	}		
}



