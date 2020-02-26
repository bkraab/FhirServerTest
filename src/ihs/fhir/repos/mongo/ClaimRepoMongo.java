package ihs.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Claim;

import ihs.fhir.maps.ClaimMap;

public class ClaimRepoMongo extends MongoBaseRepo{
	
	private ClaimMap map;
	public ClaimRepoMongo()
	{
		super("claims", ClaimMap.getInstance());
		map = ClaimMap.getInstance();
	}
	
	public void updateClaim(IdType theId, Claim claim) {		
		Document patDoc = (Document)map.mapFromFhir(claim);				
		this.updateDocument(theId, patDoc);					
	}
}


