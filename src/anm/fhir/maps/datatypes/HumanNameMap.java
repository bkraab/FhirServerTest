package anm.fhir.maps.datatypes;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.HumanName.NameUse;

public class HumanNameMap{
	public List<Document> map(List<HumanName> names){
		List<Document> ret = new ArrayList<Document>();
		
		for(HumanName name: names){
			Document d = map(name);
			ret.add(d);
		}
		
		return ret;
	}

	public List<HumanName> mapToFhir(List<Document> docs){
		List<HumanName> ret = new ArrayList<HumanName>();
		
		for(Document doc: docs){
			HumanName hn = mapToFhir(doc);
			ret.add(hn);
		}
		
		return ret;
	}
	
	public Document map(HumanName hn) {
		Document docHN = new Document();
				
		if(hn.hasUse()){
			NameUse nu = hn.getUse();
			docHN.put("use", nu.toString());
		}
		
		if(hn.hasText()){
			docHN.put("text", hn.getText());
		}
		
		if(hn.hasFamily()){
			docHN.put("family", hn.getFamily());
		}
		
		if(hn.hasGiven()){
			docHN.put("given", hn.getGivenAsSingleString());
		}
		
		if(hn.hasPrefix()){
			docHN.put("prefix", hn.getPrefixAsSingleString());
		}
		
		if(hn.hasSuffix()){
			docHN.put("suffix", hn.getSuffixAsSingleString());
		}
		
		if(hn.hasPeriod()){
			PeriodMap pm = new PeriodMap();
			Document per = pm.map(hn.getPeriod());
			docHN.put("period", per);
		}
							
		return docHN;
		
	}

	public HumanName mapToFhir(Document doc){
		HumanName name = new HumanName();
				
		if(doc.containsKey("use")){
			NameUse nu = NameUse.valueOf((doc.getString("use")));
			name.setUse(nu);			
		}
		
		if(doc.containsKey("text")){
			name.setText(doc.getString("text"));
		}
		
		if(doc.containsKey("family")){
			name.setFamily(doc.getString("family"));
		}
		
		if(doc.containsKey("given")){
			//name.setGiven(doc.getString("family"));
		}
		
		if(doc.containsKey("prefix")){
			//name.setPrefix(doc.getString("prefix"));
		}
		
		if(doc.containsKey("suffix")){
			//name.setSuffix(doc.getString("suffix"));
		}
		
		if(doc.containsKey("period")){		
			Document d = (Document)doc.get("period");
			PeriodMap pm = new PeriodMap();
			Period p = pm.mapToFhir(d);
			name.setPeriod(p);
		}
		
		return name;	
	}

}


