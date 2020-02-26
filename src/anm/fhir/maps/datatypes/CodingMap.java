package anm.fhir.maps.datatypes;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.Coding;

public class CodingMap{

	public List<Document> map(List<Coding> codes){
		List<Document> ret = new ArrayList<Document>();
		
		for(Coding cc: codes){
			Document bd = map(cc);
			ret.add(bd);
		}
						
		return ret;
	}
	
	public List<Coding> mapToFhir(List<Document> docs){
		List<Coding> ret = new ArrayList<Coding>();
		
		for(Document cc: docs){
			Coding bd = mapToFhir(cc);
			ret.add(bd);
		}
						
		return ret;
	}
	
	public Document map(Coding coding){
		
		Document docCode = new Document();		
		docCode.put("system", coding.getSystem());
		docCode.put("version", coding.getVersion());
		docCode.put("code", coding.getCode());
		docCode.put("display", coding.getDisplay());
		
		if(coding.hasUserSelected()){			
			docCode.put("userSelected",  coding.getUserSelected());
		}
					
		return docCode;
	}
	
	public Coding mapToFhir(Document doc) {
		
		Coding cod = new Coding();
		cod.setSystem(doc.getString("system"));
		cod.setVersion(doc.getString("version"));
		cod.setCode(doc.getString("code"));
		cod.setDisplay(doc.getString("display"));
		
		if(doc.containsKey("userSelected")){
			cod.setUserSelected(doc.getBoolean("userSelected"));
		}
		
		return cod;
	}
}



