package anm.fhir.resprovs;

import org.hl7.fhir.r4.model.IdType;

import anm.fhir.services.DeviceService;

import org.hl7.fhir.r4.model.Device;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;

public class DeviceRestfulResourceProvider implements IResourceProvider {

	@Override
	public Class<Device> getResourceType() {
        return Device.class;
    }
	
	@Read(version=true)
    public Device getResourceById(@IdParam IdType theId) {
		Device enc = new Device();
		enc.addIdentifier();
        return enc;
    }
	
	@Create
	public MethodOutcome createCondition(@ResourceParam Device device) {
		
		DeviceService svc = new DeviceService();
		String id = svc.createDevice(device);
	
		MethodOutcome retVal = new MethodOutcome();
		retVal.setId(new IdType("Device", id, "1"));
	   	   
		return retVal;
	}
			
}





