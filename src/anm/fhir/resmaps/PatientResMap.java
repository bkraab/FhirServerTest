package anm.fhir.resmaps;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient.ContactComponent;
import org.hl7.fhir.r4.model.Patient.PatientCommunicationComponent;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.Resource;

import anm.fhir.maps.datatypes.*;
import anm.fhir.repos.mongo.IMongoMap;

import org.hl7.fhir.r4.model.IntegerType;
import org.hl7.fhir.r4.model.Patient;

public class PatientResMap implements IMongoMap{
	
	public Document map(Resource res) {
		
		Patient pat = (Patient)res;
		Document  docPatient = new Document ();
		
		if(pat.hasIdentifier()){
			IdentifierMap map = new IdentifierMap();
			List<Document> docs = map.map(pat.getIdentifier());
			docPatient.put("identifier", docs);
		}
				
		if(pat.hasActive()){
			docPatient.put("active", pat.getActive());
		}
		
		if(pat.hasName()){
			HumanNameMap map = new HumanNameMap();
			List<Document> dl = map.map(pat.getName());
			docPatient.put("name", dl);
		}
		
		if(pat.hasTelecom()){
			ContactPointMap map = new ContactPointMap();
			List<Document> docTelcoms = map.map(pat.getTelecom());
			docPatient.put("telecom", docTelcoms);
		}
		
		if(pat.hasGender()){
			AdministrativeGender gen = pat.getGender();
			docPatient.put("gender", gen.toString());
		}
		
		if(pat.hasBirthDate()){
			docPatient.put("birthdate", pat.getBirthDate());
		}
		
		if(pat.hasDeceasedBooleanType()){
			
			BooleanType bt = pat.getDeceasedBooleanType();					
			docPatient.put("deceasedBoolean", bt.getValue());			
		}
		
		if(pat.hasDeceasedDateTimeType()){
			
			DateTimeType bt = pat.getDeceasedDateTimeType();					
			docPatient.put("deceasedDateTime", bt.getValue());			
		}
		
		if(pat.hasAddress()){
			AddressMap map = new AddressMap();
			List<Document> docAdds = map.map(pat.getAddress());
			docPatient.put("address", docAdds);
			
		}
		
		if(pat.hasMaritalStatus()){
			CodeableConceptMap ccmap = new CodeableConceptMap();
			Document docStatus = ccmap.map(pat.getMaritalStatus());
			docPatient.put("maritalStatus", docStatus);
		}
		
		if(pat.hasMultipleBirthBooleanType()){
			BooleanType bt = pat.getMultipleBirthBooleanType();					
			docPatient.put("multipleBirthBoolean", bt.getValue());	
		}
		
		if(pat.hasMultipleBirthIntegerType()){
			IntegerType bt = pat.getMultipleBirthIntegerType();					
			docPatient.put("multipleBirthInteger", bt.getValue());	
		}
		
		if(pat.hasContact()){
			ContactMap map = new ContactMap();
			List<Document> doc = map.map(pat.getContact());
			docPatient.put("contact", doc);		
		}
		
		if(pat.hasCommunication()){
			CommunicationMap cm = new CommunicationMap();
			List<Document> doc = cm.map(pat.getCommunication());
			docPatient.put("communication ", doc);
		}
			
		return docPatient;
					
	}	
	
	public Patient mapTofhir(Document doc) {
		
		Patient pat = new Patient ();
		
		if(doc.containsKey("identifier")){
			IdentifierMap map = new IdentifierMap();
			@SuppressWarnings("unchecked")
			List<Identifier> ids = map.mapToFhir((List<Document>)doc.get("identifier"));
			pat.setIdentifier(ids);
		}
		
		if(doc.containsKey("active")){
			pat.setActive(doc.getBoolean("active"));
		}
		
		if(doc.containsKey("name")){
			HumanNameMap map = new HumanNameMap();
			@SuppressWarnings("unchecked")
			List<HumanName> names = map.mapToFhir((List<Document>)doc.get("name"));
			pat.setName(names);
		}
		
		if(doc.containsKey("telecom")){
			ContactPointMap map = new ContactPointMap();
			@SuppressWarnings("unchecked")
			List<ContactPoint> cons = map.mapToFhir((List<Document>)doc.get("telecom"));
			pat.setTelecom(cons);			
		}
		
		if(doc.containsKey("gender")){
			AdministrativeGender ag = AdministrativeGender.valueOf(doc.getString("gender"));
			pat.setGender(ag);
		}
		
		if(doc.containsKey("active")){
			pat.setActive(doc.getBoolean("active"));
		}
		
		if(doc.containsKey("birthdate")){
			Date bd = doc.getDate("birthdate");
			pat.setBirthDate(bd);
		}
		
		if(doc.containsKey("deceasedBoolean")){

		}
		
		if(doc.containsKey("deceasedDateTime")){			
		}
		
		if(doc.containsKey("address")){	
			AddressMap map = new AddressMap();
			@SuppressWarnings("unchecked")
			List<Address> adds = map.mapToFhir((List<Document>)doc.get("address"));
			pat.setAddress(adds);			
		}
		
		if(doc.containsKey("maritalStatus")){	
			CodeableConceptMap ccmap = new CodeableConceptMap();
			CodeableConcept cc = ccmap.mapToFhir((Document)doc.get("maritalStatus"));
			pat.setMaritalStatus(cc);
		}
		
		if(doc.containsKey("multipleBirthBoolean")){			
		}
		
		if(doc.containsKey("multipleBirthInteger")){			
		}
		
		if(doc.containsKey("multipleBirthInteger")){			
		}
		
		if(doc.containsKey("contact")){	
			ContactMap map = new ContactMap();
			@SuppressWarnings("unchecked")
			List<ContactComponent> cons = map.mapToFhir((List<Document>)doc.get("contact"));
			pat.setContact(cons);						
		}
		
		if(doc.containsKey("communication")){	
			CommunicationMap map = new CommunicationMap();
			@SuppressWarnings("unchecked")
			List<PatientCommunicationComponent> cons = map.mapToFhir((List<Document>)doc.get("communication"));
			pat.setCommunication(cons);						
		}
		
		return pat;
					
	}	
		
}

class CommunicationMap{

	public List<Document> map(List<PatientCommunicationComponent> coms){
		
		List<Document> retList = new ArrayList<Document>(0);
		
		for(PatientCommunicationComponent pcc: coms){
			Document bdo = map(pcc);
			retList.add(bdo);
		}
		
		return retList;
	}
	
	public List<PatientCommunicationComponent> mapToFhir(List<Document> pccs){
		
		List<PatientCommunicationComponent> retList = new ArrayList<PatientCommunicationComponent>(0);
		
		for(Document pcc: pccs){
			PatientCommunicationComponent bdo = mapToFhir(pcc);
			retList.add(bdo);
		}
		
		return retList;
	}
	
	public Document map(PatientCommunicationComponent com) {
		
		Document docComm = new Document();
		
		CodeableConceptMap ccm = new CodeableConceptMap();
		Document  bo = ccm.map(com.getLanguage());
		docComm.put("language", bo);	
		
		if(com.hasPreferred())
		{			
			docComm.put("preferred", com.getPreferred());
		}
		
							
		return docComm;
	}
	
	public PatientCommunicationComponent mapToFhir(Document doc){
		PatientCommunicationComponent pcc = new PatientCommunicationComponent();
		
		if(doc.containsKey("language")){	
			CodeableConceptMap ccm = new CodeableConceptMap();
			CodeableConcept cc = ccm.mapToFhir((Document)doc.get("language"));
			pcc.setLanguage(cc);	
		}
		
		if(doc.containsKey("preferred")){
			pcc.setPreferred(doc.getBoolean("preferred"));
		}
			
		return pcc;
	}
}

class ContactMap{

	public List<Document> map(List<ContactComponent> contacts){
		List<Document> ret = new ArrayList<Document>(0);
		
		for(ContactComponent cc: contacts){
			Document bd = map(cc);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public List<ContactComponent> mapToFhir(List<Document> docs){
		List<ContactComponent> ret = new ArrayList<ContactComponent>(0);
		
		for(Document doc: docs){
			ContactComponent bd = mapToFhir(doc);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public Document map(ContactComponent contact) 
	{
		Document retDoc = new Document();
			
		if(contact.hasRelationship())
		{
			CodeableConceptMap ccmap = new CodeableConceptMap();
			List<Document> rls = ccmap.map(contact.getRelationship());
			retDoc.put("relationship ", rls);		
		}
		
		if(contact.hasName())
		{
			HumanNameMap hn = new HumanNameMap();
			Document d = hn.map(contact.getName());
			retDoc.put("name", d);			
		}
		
		if(contact.hasTelecom())
		{
			ContactPointMap cpm = new ContactPointMap();
			List<Document> tels = cpm.map(contact.getTelecom());
			retDoc.put("telecom", tels);			
		}
		
		if(contact.hasAddress())
		{
			AddressMap am = new AddressMap();
			Document bo = am.map(contact.getAddress());
			retDoc.put("address", bo);			
		}
		
		if(contact.hasGender())
		{
			AdministrativeGender ag = contact.getGender();
			retDoc.put("gender", ag.toString());						
		}
		
		if(contact.hasPeriod())
		{
			PeriodMap pm = new PeriodMap();
			Document bo = pm.map(contact.getPeriod());
			retDoc.put("period", bo);								
		}
							
		return retDoc;
	}
	
	public ContactComponent mapToFhir(Document doc) 
	{
		ContactComponent con = new ContactComponent();
		
		if(doc.containsKey("relationship")){	
			CodeableConceptMap map = new CodeableConceptMap();
			@SuppressWarnings("unchecked")
			List<CodeableConcept> rls = map.mapToFhir((List<Document>)doc.get("relationship"));
			con.setRelationship(rls);		
		}
		
		if(doc.containsKey("name")){		
			HumanNameMap map = new HumanNameMap();
			HumanName hn = map.mapToFhir((Document)doc.get("name"));
			con.setName(hn);		
		}
		
		if(doc.containsKey("telecom")){	
			ContactPointMap cpm = new ContactPointMap();
			@SuppressWarnings("unchecked")
			List<ContactPoint> tels = cpm.mapToFhir((List<Document>)doc.get("telecom"));
			con.setTelecom(tels);
		}
		
		if(doc.containsKey("address")){
			AddressMap am = new AddressMap();
			Address a = am.mapToFhir((Document)doc.get("address"));
			con.setAddress(a);	
		}
		
		if(doc.containsKey("gender")){
			AdministrativeGender g = AdministrativeGender.valueOf(doc.getString("gender"));
			con.setGender(g);
		}
		
		if(doc.containsKey("period")){
			PeriodMap am = new PeriodMap();
			Period p = am.mapToFhir((Document)doc.get("period"));
			con.setPeriod(p);	
		}
							
		return con;
	}			
}

