package anm.fhir.maps.datatypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.Period;

public class PeriodMap {

	public List<Document> map(List<Period> periods){
		
		List<Document> ret = new ArrayList<Document>();
		
		for(Period per: periods){
			Document bd = map(per);
			ret.add(bd);
		}
						
		return ret;
	}

	public List<Period> mapToFhir(List<Document> docs){
		
		List<Period> ret = new ArrayList<Period>();
		
		for(Document doc: docs){
			Period bd = mapToFhir(doc);
			ret.add(bd);
		}
						
		return ret;
	}
	
	
	public Document map(Period period) {
		
		Document docCode = new Document();		
		
		if(period.hasStart()){
			docCode.put("start ", period.getStart());						
		}
		
		if(period.hasEnd()){
			docCode.put("end ", period.getEnd());						
		}
										
		return docCode;
	}
	
	public Period mapToFhir(Document doc) {
						
		Period p = new Period();
		
		if(doc.containsKey("start")){
			Date d = doc.getDate("start");
			p.setStart(d);	
		}
		
		if(doc.containsKey("end")){
			Date d = doc.getDate("end");
			p.setEnd(d);	
		}
					
		return p;
	}
}




