package anm.fhir.maps.datatypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.StringType;

import anm.fhir.resmaps.ReferenceMap;

public class AnnotationMap{

	public List<Document> map(List<Annotation> annons){
		List<Document> ret = new ArrayList<Document>(0);
		
		for(Annotation annon: annons){
			Document bd = map(annon);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public List<Annotation> mapToFhir(List<Document> docs){
		List<Annotation> ret = new ArrayList<Annotation>(0);
		
		for(Document doc: docs){
			Annotation bd = mapToFhir(doc);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public Document map(Annotation annon){
		
		Document docAnnon = new Document();	
		
		if(annon.hasAuthorStringType()){
			docAnnon.put("authorString", annon.getAuthorStringType().toString());			
		}
		
		if(annon.hasAuthorReference()){			
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(annon.getAuthorReference());
			docAnnon.put("authorReference", bd);
		}
		
		if(annon.hasTime()){
			docAnnon.put("time", annon.getTime());			
		}
		
		
		if(annon.hasText()){
			docAnnon.put("text", annon.getText());
		}
					
		return docAnnon;
	}
	
	public Annotation mapToFhir(Document doc) {
		Annotation ret = new Annotation();
		
		if(doc.containsKey("authorString")){			
			String s = doc.getString("authorString");
			StringType st = new StringType(s);
			ret.setAuthor(st);
		}
		
		if(doc.containsKey("authorReference")){
			ReferenceMap map = new ReferenceMap();
			Reference ref = map.mapToFhir((Document)doc.get("authorReference"));			
			ret.setAuthor(ref);
		}
		
		if(doc.containsKey("time")){
			Date d = doc.getDate("time");
			ret.setTime(d);									
		}
		
		if(doc.containsKey("text")){
			ret.setText(doc.getString("text"));
		}
		
		return ret;
	}
}



