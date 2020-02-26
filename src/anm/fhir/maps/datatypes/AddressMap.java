
package anm.fhir.maps.datatypes;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Address.AddressType;
import org.hl7.fhir.r4.model.Address.AddressUse;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.StringType;

public class AddressMap {
	
	public List<Document> map(List<Address> adds)
	{
		List<Document> ret = new ArrayList<Document>();
		
		for(Address add: adds){
			Document d = map(add);
			ret.add(d);
		}						
		return ret;
	}
	
	public List<Address> mapToFhir(List<Document> docs){
		List<Address> ret = new ArrayList<Address>();
		
		for(Document doc: docs){
			Address a = mapToFhir(doc);
			ret.add(a);
		}						
		return ret;
	}
	
	public Document map(Address add) {
		Document docAdd = new Document();
		
		if(add.hasUse()) {
			docAdd.put("use", add.getUse().toString());
		}
		
		if(add.hasType()){
			docAdd.put("type", add.getType().toString());
		}
		
		if(add.hasText()){
			docAdd.put("text", add.getText());
		}
		
		if(add.hasLine()){
			List<StringType> lines = add.getLine();
			List<String> sl = new ArrayList<String>();
			
			for(int i = 0; i < lines.size(); i++){
				sl.add(lines.get(i).getValue());
			}
			
			docAdd.put("line", sl);
		}
		
		if(add.hasCity()){
			docAdd.put("city", add.getCity());
		}
		
		if(add.hasDistrict()){
			docAdd.put("district", add.getDistrict());
		}
		
		if(add.hasState()){
			docAdd.put("state", add.getState());
		}
		
		if(add.hasPostalCode()){
			docAdd.put("postalCode", add.getPostalCode());
		}
		
		if(add.hasCountry()){
			docAdd.put("country", add.getCountry());
		}
		
		if(add.hasPeriod()){
			PeriodMap pm = new PeriodMap();
			Document per = pm.map(add.getPeriod());
			docAdd.put("period", per);
		}
								
		return docAdd;
	}

	public Address mapToFhir(Document doc){
		Address add = new Address();
		
		if(doc.containsKey("use")){
			AddressUse au = AddressUse.valueOf(doc.getString("use"));
			add.setUse(au);
		}
		
		if(doc.containsKey("type")){
			AddressType au = AddressType.valueOf(doc.getString("type"));
			add.setType(au);
		}
				
		add.setText(doc.getString("text"));
		
		//TODO:  Address Line one (GET) fix
		
//		if(add.hasLine()) 
//		{
//			List<StringType> lines = add.getLine();
//			List<String> sl = new ArrayList<String>();
//			
//			for(int i = 0; i < lines.size(); i++)
//			{
//				sl.add(lines.get(i).getValue());
//			}
//			
//			docAdd.put("line", sl);
//		}
		
		add.setCity(doc.getString("city"));
		add.setDistrict(doc.getString("district"));		
		add.setState(doc.getString("state"));
		add.setPostalCode(doc.getString("postalCode"));
		add.setCountry(doc.getString("country"));
		
		if(doc.containsKey("period")){		
			Document d = (Document)doc.get("period");
			PeriodMap pm = new PeriodMap();
			Period p = pm.mapToFhir(d);
			add.setPeriod(p);
		}
		
								
		return add;
	}

}

