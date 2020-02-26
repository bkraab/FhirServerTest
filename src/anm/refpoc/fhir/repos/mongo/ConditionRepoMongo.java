package anm.refpoc.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;

import anm.refpoc.fhir.maps.ConditionMap;

import org.hl7.fhir.r4.model.Condition;

public class ConditionRepoMongo extends MongoBaseRepo{
	
	private ConditionMap map;
	public ConditionRepoMongo()
	{
		super("conditions", ConditionMap.getInstance());
		map = ConditionMap.getInstance();
	}
	
	public void updateCondition(IdType theId, Condition condition) {		
		Document doc = (Document)map.mapFromFhir(condition);				
		this.updateDocument(theId, doc);					
	}
}


