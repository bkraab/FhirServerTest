package anm.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.Condition;

import anm.fhir.resmaps.ConditionResMap;

public class ConditionRepoMongo extends MongoBaseRepo {

	public String createCondition(Condition condition) {
		
		ConditionResMap pm = new ConditionResMap();
		Document orgDoc = pm.map(condition);
		return super.createResource(orgDoc, "conditions");				
	}		
}




