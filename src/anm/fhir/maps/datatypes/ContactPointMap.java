package anm.fhir.maps.datatypes;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointUse;
import org.hl7.fhir.r4.model.Period;

public class ContactPointMap{

	public List<Document> map(List<ContactPoint> points){
		List<Document> ret = new ArrayList<Document>();
		
		for(ContactPoint cp: points){
			Document bd = map(cp);
			ret.add(bd);
		}				
						
		return ret;
	}
	
	public List<ContactPoint> mapToFhir(List<Document> docs){
		List<ContactPoint> ret = new ArrayList<ContactPoint>();
		
		for(Document doc: docs){
			ContactPoint cp = mapToFhir(doc);
			ret.add(cp);
		}				
						
		return ret;
	}
	
	public Document map(ContactPoint conPoint) {
		
		Document docCode = new Document();	
		
		if(conPoint.hasSystem()){
			
			ContactPointSystem cps = conPoint.getSystem();
			String s = cps.name();
			docCode.put("system", s);
					
		}
		
		if(conPoint.hasValue()){
			docCode.put("value", conPoint.getValue());			
		}
				
		if(conPoint.hasUse()){
			docCode.put("use", conPoint.getUse().toString());
		}
		
		if(conPoint.hasRank()){
			docCode.put("rank", conPoint.getRank());
		}
		
		if(conPoint.hasPeriod()){
			PeriodMap pm = new PeriodMap();
			Document per = pm.map(conPoint.getPeriod());	
			docCode.put("period", per);
		}
				
		return docCode;
	}
	
	public ContactPoint mapToFhir(Document doc){
		
		ContactPoint cp = new ContactPoint();	
		
		if(doc.containsKey("system")){			
			String s = doc.getString("system");
			ContactPointSystem cps = ContactPointSystem.valueOf(s);
			cp.setSystem(cps);
		}
		
		cp.setValue(doc.getString("value"));
		
		if(doc.containsKey("use")){			
			ContactPointUse cpu = ContactPointUse.valueOf(doc.getString("use"));
			cp.setUse(cpu);
		}
		
		
		if(doc.containsKey("rank")){
			Integer r = doc.getInteger("rank");
			cp.setRank(r);
		}
				
		if(doc.containsKey("period")){
			Document dp = (Document)doc.get("period");			
			PeriodMap pm = new PeriodMap();
			Period p = pm.mapToFhir(dp);	
			cp.setPeriod(p);			
		}
				
		return cp;
	}
}


