package ihs.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Procedure;

import ihs.fhir.maps.ProcedureMap;

public class ProcedureRepoMongo extends MongoBaseRepo{
	
	private ProcedureMap map;
	public ProcedureRepoMongo()
	{
		super("procedures", ProcedureMap.getInstance());
		map = ProcedureMap.getInstance();
	}
	
	public void updateProcedure(IdType theId, Procedure procedure) {		
		Document patDoc = (Document)map.mapFromFhir(procedure);				
		this.updateDocument(theId, patDoc);					
	}
}



