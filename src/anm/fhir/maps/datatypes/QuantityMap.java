package anm.fhir.maps.datatypes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Quantity.QuantityComparator;


public class QuantityMap{

	public List<Document> map(List<Quantity> quants){
		
		List<Document> ret = new ArrayList<Document>(0);
		
		for(Quantity q: quants){
			Document bd = map(q);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public List<Quantity> mapToFhir(List<Document> docs){
		
		List<Quantity> ret = new ArrayList<Quantity>(0);
		
		for(Document doc: docs){
			Quantity bd = mapToFhir(doc);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public Document map(Quantity quant) {
		
		Document docCode = new Document();	
		
		if(quant.hasValue()){
			docCode.put("value", quant.getValue());			
		}
		
		if(quant.hasComparator()){
			docCode.put("comparator", quant.getComparator());
		}
		
		if(quant.hasUnit()){
			docCode.put("unit", quant.getUnit());
		}
		
		if(quant.hasSystem()){
			docCode.put("system", quant.getSystem());
		}
		
		if(quant.hasCode()) {
			docCode.put("code", quant.getCode());
		}
		
				
		return docCode;
	}
	
	public Quantity mapToFhir(Document doc){
		
		Quantity ret = new Quantity();
		
		if(doc.containsKey("value")){
			BigDecimal bg = (BigDecimal)doc.get("value");
			ret.setValue(bg);
		}
		
		if(doc.containsKey("comparator")){
			QuantityComparator qc = (QuantityComparator)doc.get("comparator");
			ret.setComparator(qc);
		}
		
		if(doc.containsKey("unit")){
			ret.setUnit(doc.getString("unit"));
		}
		
		if(doc.containsKey("system")){
			ret.setSystem(doc.getString("system"));
		}
		
		if(doc.containsKey("code")){
			ret.setCode(doc.getString("code"));
		}
				
		return ret;
	}
}


