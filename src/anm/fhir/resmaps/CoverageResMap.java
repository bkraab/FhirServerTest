package anm.fhir.resmaps;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.Coverage;
import org.hl7.fhir.r4.model.Coverage.ClassComponent;
import org.hl7.fhir.r4.model.Coverage.CostToBeneficiaryComponent;
import org.hl7.fhir.r4.model.Coverage.ExemptionComponent;


import anm.fhir.maps.datatypes.*;

public class CoverageResMap {
	
	public List<Document> map(List<Coverage> covs)
	{
		List<Document> ret = new ArrayList<Document>(0);
		
		for(Coverage cov: covs){
			Document bd = map(cov);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public Document map(Coverage cov) {
		
		Document  docRef = new Document ();
		
		if(cov.hasIdentifier())
		{
			IdentifierMap map = new IdentifierMap();
			List<Document> bd = map.map(cov.getIdentifier());
			docRef.put("identifier", bd);
		}
		
		if(cov.hasStatus())
		{
			docRef.put("status", cov.getStatus());			
		}
		
		if(cov.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(cov.getType());
			docRef.put("type", bd);			
		}
		
		if(cov.hasPolicyHolder ())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(cov.getPolicyHolder());
			docRef.put("policyHolder", bd);
		}
		
		if(cov.hasSubscriberId())
		{
			docRef.put("subscriberId", cov.getSubscriberId());
		}
		
		if(cov.hasBeneficiary())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(cov.getBeneficiary());
			docRef.put("beneficiary", bd);
		}
		
		if(cov.hasDependent())
		{
			docRef.put("dependent", cov.getDependent());
		}
		
		if(cov.hasRelationship ())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(cov.getRelationship ());
			docRef.put("relationship", bd);			
		}
		
		if(cov.hasPeriod())
		{
			PeriodMap map = new PeriodMap();
			Document bd = map.map(cov.getPeriod());
			docRef.put("period", bd);			
		}
		
		if(cov.hasPayor())
		{
			ReferenceMap map = new ReferenceMap();
			List<Document> bd = map.map(cov.getPayor());
			docRef.put("payor", bd);			
		}
		
		if(cov.hasClass_())
		{
			ClassComponentMap2 map = new ClassComponentMap2();
			List<Document> bd = map.map(cov.getClass_());
			docRef.put("class", bd);			
		}
		
		if(cov.hasOrder())
		{
			docRef.put("order", cov.getOrder());
		}
		
		if(cov.hasNetwork())
		{
			docRef.put("network", cov.getNetwork());
		}
		
		if(cov.hasCostToBeneficiary())
		{
			CostToBeneficiaryComponentMap2 map = new CostToBeneficiaryComponentMap2();
			List<Document> bd = map.map(cov.getCostToBeneficiary());
			docRef.put("costToBeneficiary", bd);			
		}
		
		if(cov.hasSubrogation())
		{
			docRef.put("subrogation", cov.getSubrogation());
		}
		
		if(cov.hasContract())
		{
			ReferenceMap map = new ReferenceMap();
			List<Document> bd = map.map(cov.getContract());
			docRef.put("contract", bd);			
		}
							
		return docRef;
						
	}	
}

class ClassComponentMap2{
	
	public List<Document> map(List<ClassComponent> classes)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(ClassComponent cls: classes){
			Document bdo = map(cls);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(ClassComponent cls) {
		
		Document  doc = new Document ();
		
		if(cls.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(cls.getType());
			doc.put("type", bd);			
		}
		
		if(cls.hasValue())
		{
			doc.put("value", cls.getValue());
		}
		
		if(cls.hasName())
		{
			doc.put("name", cls.getName());
		}
						
		return doc;					
	}	
}

class CostToBeneficiaryComponentMap2{
	
	public List<Document> map(List<CostToBeneficiaryComponent> costs)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(CostToBeneficiaryComponent cost: costs){
			Document bdo = map(cost);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(CostToBeneficiaryComponent cost) {
		
		Document  doc = new Document ();
		
		if(cost.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(cost.getType());
			doc.put("type", bd);			
		}
		
		if(cost.hasValueQuantity())
		{
			QuantityMap map = new QuantityMap();
			Document bd = map.map(cost.getValueQuantity());
			doc.put("value", bd);									
		}
		
		if(cost.hasValueMoney())
		{
			MoneyMap map = new MoneyMap();
			Document bd = map.map(cost.getValueMoney());
			doc.put("value", bd);			
		}
		
		if(cost.hasException())
		{
			ExemptionComponentMap2 map = new ExemptionComponentMap2();
			List<Document> bd = map.map(cost.getException());
			doc.put("exception", bd);	
			
		}
						
		return doc;					
	}	
}

class ExemptionComponentMap2{
	
	public List<Document> map(List<ExemptionComponent> comps)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(ExemptionComponent comp: comps){
			Document bdo = map(comp);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(ExemptionComponent comp) {
		
		Document  doc = new Document ();
		
		if(comp.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(comp.getType());
			doc.put("type", bd);			
		}
		
		if(comp.hasPeriod())
		{
			PeriodMap map = new PeriodMap();
			Document bd = map.map(comp.getPeriod());
			doc.put("period", bd);
		}
								
		return doc;					
	}	
}










