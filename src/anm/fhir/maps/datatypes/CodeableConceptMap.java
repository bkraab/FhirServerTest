package anm.fhir.maps.datatypes;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;


public class CodeableConceptMap {

	public List<Document> map(List<CodeableConcept> ccs){
		List<Document> ret = new ArrayList<Document>();
		
		for(CodeableConcept cc: ccs){
			Document bd = map(cc);
			ret.add(bd);
		}
						
		return ret;
	}
	
	public List<CodeableConcept> mapToFhir(List<Document> ccs){
		List<CodeableConcept> ret = new ArrayList<CodeableConcept>();
		
		for(Document cc: ccs){
			CodeableConcept bd = mapToFhir(cc);
			ret.add(bd);
		}
						
		return ret;
	}
	
	public Document map(CodeableConcept ccon) {
		
		Document doc = new Document();		
		
		CodingMap cm = new CodingMap();
		List<Document> docs = cm.map(ccon.getCoding());				
		doc.put("coding", docs);
		doc.put("text", ccon.getText());
		
		return doc;
		
	}

	public CodeableConcept mapToFhir(Document doc) {
		
		CodeableConcept cc = new CodeableConcept();		
		
		CodingMap cm = new CodingMap();
		
		@SuppressWarnings("unchecked")
		List<Coding> codings = cm.mapToFhir((List<Document>)doc.get("coding"));
		cc.setCoding(codings);
		cc.setText(doc.getString("text"));
		
		return cc;
		
	}
}


