package anm.fhir.resmaps;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Organization.OrganizationContactComponent;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StringType;

import anm.fhir.maps.datatypes.*;
import anm.fhir.repos.mongo.IMongoMap;

public class OrganizationResMap implements IMongoMap {
	
	public Document map(Resource res) {
		
		Organization org = (Organization)res;
		Document  docOrg = new Document ();
		
		if(org.hasIdentifier()){
			IdentifierMap map = new IdentifierMap();
			List<Document> bdl = map.map(org.getIdentifier());
			docOrg.put("identifier", bdl);
		}
				
		if(org.hasActive()){
			docOrg.put("active", org.getActive());
		}
		
		if(org.hasType()){
			CodeableConceptMap ccmap = new CodeableConceptMap();
			List<Document> docCodeConcept = ccmap.map(org.getType());
			docOrg.put("type", docCodeConcept);											
		}
		
		if(org.hasName()){
			docOrg.put("name", org.getName());
		}
		
		if(org.hasAlias()){
			docOrg.put("alias", org.getAlias());
		}
		
		if(org.hasTelecom()){
			ContactPointMap cpmap = new ContactPointMap();
			List<Document> docCodeConcept = cpmap.map(org.getTelecom());
			docOrg.put("telecom", docCodeConcept);
		}
		
		if(org.hasAddress()){
			AddressMap map = new AddressMap();
			List<Document> docAdds = map.map(org.getAddress());
			docOrg.put("address", docAdds);			
		}
		
		// need PartOF
		
		if(org.hasContact()){
			OrganizationContactComponentMap occMap = new OrganizationContactComponentMap();
			List<Document> doc = occMap.map(org.getContact());
			docOrg.put("contact", doc);		
		}
		
		return docOrg;
					
	}	
	
	public Organization mapTofhir(Document doc) {
		
		Organization  ret = new Organization ();
		
		if(doc.containsKey("identifier")){
			IdentifierMap map = new IdentifierMap();
			@SuppressWarnings("unchecked")
			List<Identifier> obj = map.mapToFhir((List<Document>) doc.get("identifier"));
			ret.setIdentifier(obj);			
		}
		
		if(doc.containsKey("active")){
			ret.setActive(doc.getBoolean("active"));			
		}
		
		if(doc.containsKey("type")){
			CodeableConceptMap ccmap = new CodeableConceptMap();
			@SuppressWarnings("unchecked")
			List<CodeableConcept> obj = ccmap.mapToFhir((List<Document>) doc.get("type"));
			ret.setType(obj);	
		}
		
		if(doc.containsKey("name")){
			ret.setName(doc.getString("name"));
		}
		
		if(doc.containsKey("alias")){
			@SuppressWarnings("unchecked")
			List<StringType> obj = (List<StringType>)doc.get("alias");
			ret.setAlias(obj);
		}
		
		if(doc.containsKey("telecom")){
			ContactPointMap cpmap = new ContactPointMap();
			@SuppressWarnings("unchecked")
			List<ContactPoint> obj = cpmap.mapToFhir((List<Document>)doc.get("telecom"));
			ret.setTelecom(obj);
		}
		
		if(doc.containsKey("address")){
			AddressMap map = new AddressMap();
			@SuppressWarnings("unchecked")
			List<Address> obj = map.mapToFhir((List<Document>)doc.get("address"));
			ret.setAddress(obj);
		}
		
		if(doc.containsKey("contact")){
			OrganizationContactComponentMap occMap = new OrganizationContactComponentMap();
			@SuppressWarnings("unchecked")
			List<OrganizationContactComponent> obj = occMap.mapToFhir((List<Document>)doc.get("address"));
			ret.setContact(obj);
		}
		
		return ret;
					
	}	
}

class OrganizationContactComponentMap{

	public List<Document> map(List<OrganizationContactComponent> orgContacts){
		List<Document> ret = new ArrayList<Document>(0);
		
		for(OrganizationContactComponent occ: orgContacts){
			Document bd = map(occ);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public List<OrganizationContactComponent> mapToFhir(List<Document> docs){
		List<OrganizationContactComponent> ret = new ArrayList<OrganizationContactComponent>(0);
		
		for(Document doc: docs){
			OrganizationContactComponent bd = mapToFhir(doc);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public Document map(OrganizationContactComponent contact){
		Document docContact = new Document();
			
		if(contact.hasPurpose()){
			CodeableConceptMap ccm = new CodeableConceptMap();
			Document obj = ccm.map(contact.getPurpose());						
			docContact.put("purpose ", obj);		
		}
		
		if(contact.hasName()){
			HumanNameMap hn = new HumanNameMap();
			Document name = hn.map(contact.getName());
			docContact.put("name", name);			
		}
		
		if(contact.hasTelecom()){
			ContactPointMap map = new ContactPointMap();
			List<Document> l = map.map(contact.getTelecom());
			docContact.put("telecom", l);			
		}
		
		if(contact.hasAddress()){
			AddressMap am = new AddressMap();
			Document bo = am.map(contact.getAddress());
			docContact.put("address", bo);			
		}
							
		return docContact;
	}	
	
	public OrganizationContactComponent mapToFhir(Document doc){
		
		OrganizationContactComponent ret = new OrganizationContactComponent();
		
		if(doc.containsKey("purpose")){
			CodeableConceptMap ccm = new CodeableConceptMap();
			CodeableConcept obj = ccm.mapToFhir((Document)doc.get("purpose"));						
			ret.setPurpose(obj);
		}
		
		if(doc.containsKey("name")){
			HumanNameMap hn = new HumanNameMap();
			HumanName obj = hn.mapToFhir((Document)doc.get("name"));
			ret.setName(obj);
		}
		
		if(doc.containsKey("telecom")){
			ContactPointMap map = new ContactPointMap();
			@SuppressWarnings("unchecked")
			List<ContactPoint> obj = map.mapToFhir((List<Document>)doc.get("telecom"));
			ret.setTelecom(obj);			
		}
		
		if(doc.containsKey("address")){
			AddressMap am = new AddressMap();
			Address obj = am.mapToFhir((Document)doc.get("address"));
			ret.setAddress(obj);
		}
							
		return ret;
	}
}



