package anm.fhir.services;

import org.hl7.fhir.r4.model.Device;

import anm.fhir.repos.mongo.DeviceRepoMongo;

public class DeviceService {

	public String createDevice(Device device){
		
		DeviceRepoMongo repo = new DeviceRepoMongo();
		String id = repo.create(device);
		return id;		
	}
}



