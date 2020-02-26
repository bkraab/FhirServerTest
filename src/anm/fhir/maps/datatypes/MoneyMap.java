package anm.fhir.maps.datatypes;

import org.bson.Document;
import org.hl7.fhir.r4.model.Money;

public class MoneyMap{

	public Document map(Money money){
		
		Document docCode = new Document();	
		
		if(money.hasValue()){
			docCode.put("value", money.getValue());			
		}
		
		if(money.hasCurrency()){
			docCode.put("currency", money.getCurrency());
		}
		
		return docCode;
	}
	
	public Money mapToFhir(Document doc){
		
		Money mon = new Money();	
		
		if(doc.containsKey("value")){
			mon.setValue(doc.getLong("value"));					
		}
		
		if(doc.containsKey("currency")){
			mon.setCurrency(doc.getString("currency"));					
		}		
		
		return mon;
	}
}




