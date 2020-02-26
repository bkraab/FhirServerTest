package anm.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.Resource;

public interface IMongoMap {

	Document map(Resource pat);
	Resource mapTofhir(Document doc);
}
