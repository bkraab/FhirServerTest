package anm.fhir.services;

import org.hl7.fhir.r4.model.Coverage;

import anm.fhir.repos.mongo.CoverageRepoMongo;

public class CoverageService {

	public String createCoverage(Coverage coverage){
		
		CoverageRepoMongo repo = new CoverageRepoMongo();
		String id = repo.createCoverage(coverage);
		return id;		
	}
}



