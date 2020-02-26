package anm.fhir.resmaps;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Reference;


import anm.fhir.maps.datatypes.*;

public class ReferenceMap {
	
	public List<Document> map(List<Reference> refs){
		
		List<Document> ret = new ArrayList<Document>(0);
		
		for(Reference ref: refs){
			Document bd = map(ref);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public List<Reference> mapToFhir(List<Document> docs){
		
		List<Reference> ret = new ArrayList<Reference>(0);
		
		for(Document ref: docs){
			Reference bd = mapToFhir(ref);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public Document map(Reference refer) {
			
		Document  docRef = new Document ();
		
		if(refer.hasReference()){
			docRef.put("reference", refer.getReference());			
		}
		
		if(refer.hasType()){
			docRef.put("type", refer.getType());			
		}
						
		if(refer.hasIdentifier()){
			IdentifierMap map = new IdentifierMap();
			Document bd = map.map(refer.getIdentifier());
			docRef.put("identifier", bd);
		}
					
		if(refer.hasDisplay()){
			docRef.put("display", refer.getDisplay());
		}
			
		return docRef;
						
	}	
	
	public Reference mapToFhir(Document doc) {
		
		Reference  ref = new Reference ();
		
		if(doc.containsKey("reference")){
			ref.setReference(doc.getString("reference"));
		}
		
		if(doc.containsKey("type")){
			ref.setType(doc.getString("type"));
		}
		
		if(doc.containsKey("identifier")){
			IdentifierMap map = new IdentifierMap();
			Identifier id = map.mapToFhir((Document)doc.get("identifier"));
			ref.setIdentifier(id);
		}
		
		if(doc.containsKey("display")){
			ref.setDisplay(doc.getString("display"));
		}
		return ref;
						
	}
}


