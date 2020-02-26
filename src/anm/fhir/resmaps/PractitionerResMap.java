package anm.fhir.resmaps;

import org.hl7.fhir.r4.model.Practitioner;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;

import anm.fhir.maps.datatypes.*;

public class PractitionerResMap {
		
	public Document map(Practitioner pract) {
			
		Document  docPract = new Document ();
			
		if(pract.hasIdentifier()){
			IdentifierMap map = new IdentifierMap();
			List<Document> bdl = map.map(pract.getIdentifier());
			docPract.put("identifier", bdl);
		}
					
		if(pract.hasActive()){
			docPract.put("active", pract.getActive());
		}
		
		if(pract.hasName()){
			HumanNameMap map = new HumanNameMap();
			List<Document> docAdds = map.map(pract.getName());
			docPract.put("name", docAdds);
									
		}
		
		if(pract.hasTelecom()){
			ContactPointMap cpmap = new ContactPointMap();
			List<Document> docCodeConcept = cpmap.map(pract.getTelecom());
			docPract.put("telecom", docCodeConcept);
		}
			
		if(pract.hasAddress()){
			AddressMap map = new AddressMap();
			List<Document> docAdds = map.map(pract.getAddress());
			docPract.put("address", docAdds);
			
		}
		
		if(pract.hasGender()){
			AdministrativeGender gen = pract.getGender();
			docPract.put("gender", gen.toString());
		}
		
		if(pract.hasBirthDate()){
			docPract.put("birthdate", pract.getBirthDate());
		}
		
		//Neeed Photo
		
		if(pract.hasCommunication()){
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> docAdds = map.map(pract.getCommunication());
			docPract.put("communication", docAdds);
		}
							
		return docPract;
						
	}	
	
	public Practitioner mapToFhir(Document doc) {
		
		Practitioner  ret = new Practitioner ();
		
		if(doc.containsKey("identifier")){
			IdentifierMap map = new IdentifierMap();
			@SuppressWarnings("unchecked")
			List<Identifier> ids = map.mapToFhir((List<Document>)doc.get("identifier"));
			ret.setIdentifier(ids);
		}
		
		if(doc.containsKey("active")){
			ret.setActive(doc.getBoolean("active"));
		}

		if(doc.containsKey("name")){
			HumanNameMap map = new HumanNameMap();
			@SuppressWarnings("unchecked")
			List<HumanName> names = map.mapToFhir((List<Document>)doc.get("name"));
			ret.setName(names);
		}
		
		if(doc.containsKey("telecom")){
			ContactPointMap cpmap = new ContactPointMap();
			@SuppressWarnings("unchecked")
			List<ContactPoint> cps = cpmap.mapToFhir((List<Document>)doc.get("telecom"));
			ret.setTelecom(cps);
		}
		
		if(doc.containsKey("address")){
			AddressMap map = new AddressMap();
			@SuppressWarnings("unchecked")
			List<Address> adds = map.mapToFhir((List<Document>)doc.get("address"));
			ret.setAddress(adds);		
		}
		
		if(doc.containsKey("gender")){
			AdministrativeGender gen = AdministrativeGender.fromCode(doc.getString("gender"));
			ret.setGender(gen);
		}
		
		if(doc.containsKey("birthdate")){			
			Date d = doc.getDate("birthdate");
			ret.setBirthDate(d);			
		}
		
		if(doc.containsKey("communication")){
			CodeableConceptMap map = new CodeableConceptMap();
			@SuppressWarnings("unchecked")
			List<CodeableConcept> ccs = map.mapToFhir((List<Document>)doc.get("communication"));
			ret.setCommunication(ccs);
		}
										
		return ret;						
	}
}

