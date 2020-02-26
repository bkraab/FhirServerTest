package anm.refpoc.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;

import anm.refpoc.fhir.maps.ClaimMap;

import org.hl7.fhir.r4.model.Claim;

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


