package anm.fhir.maps.datatypes;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Identifier.IdentifierUse;
import org.hl7.fhir.r4.model.Period;


public class IdentifierMap{

	public List<Document> map(List<Identifier> identfiers){
		
		List<Document> ret = new ArrayList<Document>();
		
		for(Identifier id: identfiers){
			Document bd = map(id);
			ret.add(bd);
		}
						
		return ret;
	}
	
	public List<Identifier> mapToFhir(List<Document> docIds){
		List<Identifier> ret = new ArrayList<Identifier>();
		
		for(Document doc: docIds){
			Identifier id = mapToFhir(doc);
			ret.add(id);
		}
						
		return ret;
	}
	
	
	public Document map(Identifier ident) {
		
		Document docIdentifier = new Document();	
		
		if(ident.hasUse()) {			
			docIdentifier.put("use", ident.getUse().toString());			
		}
		
		CodeableConceptMap ccmap = new CodeableConceptMap();
		Document docCodeConcept = ccmap.map(ident.getType());
		docIdentifier.put("type", docCodeConcept);
			
		docIdentifier.put("system", ident.getSystem());		
		docIdentifier.put("value", ident.getValue().toString());
		
		if(ident.hasPeriod()){
			PeriodMap pm = new PeriodMap();
			Document per = pm.map(ident.getPeriod());
			docIdentifier.put("period", per);
		}
						
		return docIdentifier;
	}

	public Identifier mapToFhir(Document doc) {
		
		Identifier id = new Identifier();
		
		if(doc.containsKey("use")){
			IdentifierUse iu = IdentifierUse.valueOf(doc.getString("use"));			
			id.setUse(iu);
		}
		
		if(doc.containsKey("type")){
			CodeableConceptMap ccmap = new CodeableConceptMap();
			CodeableConcept cc = ccmap.mapToFhir((Document)doc.get("type"));
			id.setType(cc);
		}
		
		
		id.setSystem(doc.getString("system"));
		id.setValue(doc.getString("value"));
			
		if(doc.containsKey("period")){
			PeriodMap pm = new PeriodMap();
			Period p = pm.mapToFhir((Document)doc.get("period"));
			id.setPeriod(p);
		}
						
		return id;
	}
}


