
package anm.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.Coverage;

import anm.fhir.resmaps.CoverageResMap;

public class CoverageRepoMongo extends MongoBaseRepo {

	public String createCoverage(Coverage coverage) {
		
		CoverageResMap pm = new CoverageResMap();
		Document orgDoc = pm.map(coverage);
		return super.createResource(orgDoc, "coverages");				
	}		
}





