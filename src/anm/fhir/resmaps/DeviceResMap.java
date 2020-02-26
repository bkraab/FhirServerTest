package anm.fhir.resmaps;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.Device;

import org.hl7.fhir.r4.model.Device.DeviceDeviceNameComponent;
import org.hl7.fhir.r4.model.Device.DevicePropertyComponent;
import org.hl7.fhir.r4.model.Device.DeviceSpecializationComponent;
import org.hl7.fhir.r4.model.Device.DeviceUdiCarrierComponent;
import org.hl7.fhir.r4.model.Device.DeviceVersionComponent;

import anm.fhir.maps.datatypes.*;

public class DeviceResMap {
	
	public List<Document> map(List<Device> devices)
	{
		List<Document> ret = new ArrayList<Document>(0);
		
		for(Device con : devices){
			Document bd = map(con);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public Document map(Device dev) {
			
		Document  docDev = new Document ();
		
		if(dev.hasIdentifier())
		{
			IdentifierMap map = new IdentifierMap();
			List<Document> bd = map.map(dev.getIdentifier());
			docDev.put("identifier", bd);
		}
		
		if(dev.hasDefinition())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(dev.getDefinition());
			docDev.put("definition", bd);							
		}
		
		if(dev.hasUdiCarrier())
		{
			DeviceUdiCarrierComponentMap2 map = new DeviceUdiCarrierComponentMap2();
			List<Document> bd = map.map(dev.getUdiCarrier());
			docDev.put("udiCarrier", bd);							
		}
		
		if(dev.hasStatus())
		{
			docDev.put("status", dev.getStatus().toString());							
		}
					
		if(dev.hasStatusReason())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bd = map.map(dev.getStatusReason());
			docDev.put("statusReason", bd);							
		}
								
		if(dev.hasDistinctIdentifier())
		{
			docDev.put("distinctIdentifier", dev.getDistinctIdentifier());							
		}
		
		if(dev.hasManufacturer())
		{
			docDev.put("manufacturer", dev.getManufacturer());							
		}
		
		if(dev.hasManufactureDate())
		{
			docDev.put("manufactureDate", dev.getManufactureDate());							
		}
		
		if(dev.hasExpirationDate())
		{
			docDev.put("expirationDate", dev.getExpirationDate());							
		}
		
		if(dev.hasLotNumber())
		{
			docDev.put("lotNumber", dev.getLotNumber());							
		}
		
		if(dev.hasSerialNumber())
		{
			docDev.put("serialNumber", dev.getSerialNumber());							
		}
		
		if(dev.hasDeviceName())
		{
			DeviceDeviceNameComponentMap2 map = new DeviceDeviceNameComponentMap2();
			List<Document> bd = map.map(dev.getDeviceName());
			docDev.put("deviceName", bd);							
		}
		
		if(dev.hasModelNumber())
		{
			docDev.put("modelNumber", dev.getModelNumber());							
		}
		
		if(dev.hasPartNumber())
		{
			docDev.put("partNumber", dev.getPartNumber());							
		}
		
		if(dev.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(dev.getType());
			docDev.put("type", bd);							
		}
		
		if(dev.hasSpecialization())
		{
			DeviceSpecializationComponentMap2 map = new DeviceSpecializationComponentMap2();
			List<Document> bd = map.map(dev.getSpecialization());
			docDev.put("specialization", bd);							
		}
		
		if(dev.hasVersion())
		{
			DeviceVersionComponentMap2 map = new DeviceVersionComponentMap2();
			List<Document> bd = map.map(dev.getVersion());
			docDev.put("version", bd);							
		}
		
		if(dev.hasProperty())
		{
			DevicePropertyComponentMap2 map = new DevicePropertyComponentMap2();
			List<Document> bd = map.map(dev.getProperty());
			docDev.put("property", bd);							
		}
		
		if(dev.hasPatient())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(dev.getPatient());
			docDev.put("patient", bd);							
		}
		
		if(dev.hasOwner())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(dev.getOwner());
			docDev.put("owner", bd);							
		}
		
		if(dev.hasContact())
		{
			ContactPointMap map = new ContactPointMap();
			List<Document> bd = map.map(dev.getContact());
			docDev.put("contact", bd);							
		}
		
		if(dev.hasLocation())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(dev.getLocation());
			docDev.put("location", bd);
		}
		
		if(dev.hasUrl())
		{
			docDev.put("url", dev.getUrl());							
		}
		
		if(dev.hasNote())
		{
			AnnotationMap map = new AnnotationMap();
			List<Document> bd = map.map(dev.getNote());
			docDev.put("note", bd);
		}
		
		if(dev.hasSafety())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bd = map.map(dev.getSafety());
			docDev.put("safety", bd);							
		}
		
		if(dev.hasParent())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(dev.getParent());
			docDev.put("parent", bd);
		}
		
												
		return docDev;						
	}	
}

class DeviceUdiCarrierComponentMap2{
	
	public List<Document> map(List<DeviceUdiCarrierComponent> coms)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(DeviceUdiCarrierComponent stage: coms){
			Document bdo = map(stage);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(DeviceUdiCarrierComponent com) {
		
		Document  docDiag = new Document ();
		
		if(com.hasDeviceIdentifier ())
		{			
			docDiag.put("deviceIdentifier", com.getDeviceIdentifier());			
		}
		
		if(com.hasIssuer())
		{			
			docDiag.put("issuer", com.getIssuer());			
		}
		
		if(com.hasJurisdiction())
		{			
			docDiag.put("jurisdiction", com.getJurisdiction());			
		}
		
		if(com.hasCarrierAIDC())
		{			
			docDiag.put("carrierAIDC", com.getCarrierAIDC());			
		}
		
		if(com.hasCarrierHRF())
		{			
			docDiag.put("carrierHRF", com.hasCarrierHRF());			
		}
		
		if(com.hasEntryType())
		{			
			docDiag.put("entryType", com.getEntryType());			
		}
		
								
		return docDiag;					
	}	
}

class DeviceDeviceNameComponentMap2{
	
	public List<Document> map(List<DeviceDeviceNameComponent> coms)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(DeviceDeviceNameComponent stage: coms){
			Document bdo = map(stage);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(DeviceDeviceNameComponent com) {
		
		Document  docDiag = new Document ();
		
		if(com.hasName())
		{			
			docDiag.put("name", com.getName());			
		}
		
		if(com.hasType())
		{			
			docDiag.put("type", com.getType().toString());			
		}
										
		return docDiag;					
	}	
}

class DeviceSpecializationComponentMap2{
	
	public List<Document> map(List<DeviceSpecializationComponent> coms)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(DeviceSpecializationComponent stage: coms){
			Document bdo = map(stage);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(DeviceSpecializationComponent com) {
		
		Document  docCom = new Document ();		
		
		if(com.hasSystemType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(com.getSystemType());
			docCom.put("systemType", bd);							
		}
		
		if(com.hasVersion())
		{			
			docCom.put("version", com.getVersion());			
		}
										
		return docCom;					
	}	
}

class DeviceVersionComponentMap2{
	
	public List<Document> map(List<DeviceVersionComponent> coms)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(DeviceVersionComponent stage: coms){
			Document bdo = map(stage);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(DeviceVersionComponent com) {
		
		Document  docCom = new Document ();
		
		if(com.hasType())
		{			
			docCom.put("type", com.getType());			
		}
		
		if(com.hasComponent())
		{
			IdentifierMap map = new IdentifierMap();
			Document bd = map.map(com.getComponent());
			docCom.put("identifier", bd);
		}
		
		if(com.hasValue())
		{			
			docCom.put("value", com.getValue());			
		}
										
		return docCom;					
	}	
}

class DevicePropertyComponentMap2{
	
	public List<Document> map(List<DevicePropertyComponent> coms)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(DevicePropertyComponent stage: coms){
			Document bdo = map(stage);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(DevicePropertyComponent com) {
		
		Document  docCom = new Document ();
		
		if(com.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(com.getType());
			docCom.put("type", bd);			
		}
		
		if(com.hasValueQuantity())
		{
			QuantityMap map = new QuantityMap();
			List<Document> bd = map.map(com.getValueQuantity());
			docCom.put("valueQuantity", bd);			
		}
		
		if(com.hasValueCode())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bd = map.map(com.getValueCode());
			docCom.put("valueCode", bd);			
		}
		
		return docCom;					
	}	
}





