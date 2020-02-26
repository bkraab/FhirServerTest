package anm.refpoc.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Organization;

import anm.refpoc.fhir.maps.OrganizationMap;

public class OrganizationRepoMongo extends MongoBaseRepo{
	
	private OrganizationMap map;
	public OrganizationRepoMongo()
	{
		super("organizations", OrganizationMap.getInstance());
		map = OrganizationMap.getInstance();
	}
	
	public void updateOrganization(IdType theId, Organization organization) {		
		Document patDoc = (Document)map.mapFromFhir(organization);				
		this.updateDocument(theId, patDoc);					
	}
}



