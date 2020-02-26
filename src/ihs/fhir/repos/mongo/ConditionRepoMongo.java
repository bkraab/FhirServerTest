package ihs.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Condition;

import ihs.fhir.maps.ConditionMap;

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


