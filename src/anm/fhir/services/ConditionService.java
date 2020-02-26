package anm.fhir.services;

import org.hl7.fhir.r4.model.Condition;

import anm.fhir.repos.mongo.ConditionRepoMongo;

public class ConditionService {

	public String createCondition(Condition condition){
		
		ConditionRepoMongo repo = new ConditionRepoMongo();
		String id = repo.createCondition(condition);
		return id;		
	}
}


