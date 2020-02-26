package anm.fhir.repos.mongo;

import org.bson.Document;
import org.hl7.fhir.r4.model.Device;

import anm.fhir.resmaps.DeviceResMap;

public class DeviceRepoMongo extends MongoBaseRepo {

	public String create(Device device) {
		
		DeviceResMap pm = new DeviceResMap();
		Document orgDevice = pm.map(device);
		return super.createResource(orgDevice, "devices");				
	}		
}





