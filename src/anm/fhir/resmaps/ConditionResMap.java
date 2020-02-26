package anm.fhir.resmaps;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Condition.ConditionStageComponent;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Condition.ConditionEvidenceComponent;

import anm.fhir.maps.datatypes.*;

public class ConditionResMap {
	
	public List<Document> map(List<Condition> cons)
	{
		List<Document> ret = new ArrayList<Document>(0);
		
		for(Condition con : cons){
			Document bd = map(con);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public List<Condition> mapToFhir(List<Document> docs)
	{
		List<Condition> ret = new ArrayList<Condition>(0);
		
		for(Document doc : docs){
			Condition bd = mapToFhir(doc);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public Document map(Condition con) {
			
		Document  docCon = new Document ();
		
		if(con.hasIdentifier())
		{
			IdentifierMap map = new IdentifierMap();
			List<Document> bd = map.map(con.getIdentifier());
			docCon.put("identifier", bd);
		}
					
		if(con.hasClinicalStatus())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(con.getClinicalStatus());
			docCon.put("clinicalStatus", bd);							
		}
		
		if(con.hasVerificationStatus())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(con.getVerificationStatus());
			docCon.put("verificationStatus", bd);							
		}
		
		if(con.hasCategory())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bd = map.map(con.getCategory());
			docCon.put("category", bd);							
		}
		
		if(con.hasSeverity())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(con.getSeverity());
			docCon.put("severity", bd);							
		}
		
		if(con.hasCode())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(con.getCode());
			docCon.put("code", bd);							
		}
		
		if(con.hasBodySite())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bd = map.map(con.getBodySite());
			docCon.put("bodySite", bd);							
		}
		
		if(con.hasEncounter())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(con.getEncounter());
			docCon.put("encounter", bd);							
		}
		
		if(con.hasOnset()){
			
		}
		
		if(con.hasAbatement()){
			
		}
		
		if(con.hasRecordedDate()){
			docCon.put("recordedDate", con.getRecordedDate());
		}
		
		if(con.hasRecorder ())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(con.getRecorder ());
			docCon.put("recorder", bd);							
		}
		
		if(con.hasAsserter ())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(con.getAsserter ());
			docCon.put("asserter", bd);							
		}
		
		if(con.hasStage())
		{
			ConditionStageComponentMap map = new ConditionStageComponentMap();
			List<Document> bd = map.map(con.getStage());
			docCon.put("stage", bd);							
		}
		
		if(con.hasEvidence())
		{
			ConditionEvidenceComponentMap map = new ConditionEvidenceComponentMap();
			List<Document> bd = map.map(con.getEvidence());
			docCon.put("evidence", bd);
		}
												
		return docCon;						
	}	
	
	public Condition mapToFhir(Document doc) {
		
		Condition ret = new Condition ();
		
		if(doc.containsKey("identifier")){		
			IdentifierMap map = new IdentifierMap();
			@SuppressWarnings("unchecked")
			List<Identifier> obj = map.mapToFhir((List<Document>)doc.get("identifier"));
			ret.setIdentifier(obj);
		}
					
		if(doc.containsKey("clinicalStatus")){
			CodeableConceptMap map = new CodeableConceptMap();
			CodeableConcept obj = map.mapToFhir((Document)doc.get("clinicalStatus"));
			ret.setClinicalStatus(obj);							
		}
		
		if(doc.containsKey("verificationStatus")){
			CodeableConceptMap map = new CodeableConceptMap();
			CodeableConcept obj = map.mapToFhir((Document)doc.get("verificationStatus"));
			ret.setVerificationStatus(obj);								
		}
		
		if(doc.containsKey("category")){
			CodeableConceptMap map = new CodeableConceptMap();
			@SuppressWarnings("unchecked")
			List<CodeableConcept> obj = map.mapToFhir((List<Document>)doc.get("category"));
			ret.setCategory(obj);						
		}
		
		if(doc.containsKey("severity")){
			CodeableConceptMap map = new CodeableConceptMap();
			CodeableConcept obj = map.mapToFhir((Document)doc.get("severity"));
			ret.setSeverity(obj);									
		}
		
		if(doc.containsKey("code")){
			CodeableConceptMap map = new CodeableConceptMap();
			CodeableConcept obj = map.mapToFhir((Document)doc.get("code"));
			ret.setCode(obj);											
		}
		
		if(doc.containsKey("bodySite")){
			CodeableConceptMap map = new CodeableConceptMap();
			@SuppressWarnings("unchecked")
			List<CodeableConcept> obj = map.mapToFhir((List<Document>)doc.get("bodySite"));
			ret.setBodySite(obj);						
		}
		
		if(doc.containsKey("encounter")){
			ReferenceMap map = new ReferenceMap();
			Reference obj = map.mapToFhir((Document)doc.get("encounter"));
			ret.setEncounter(obj);																					
		}
		
		if(doc.containsKey("Onset")){	
		}
		
		if(doc.containsKey("Abatement")){
			
		}
		
		if(doc.containsKey("recordedDate")){
			ret.setRecordedDate(doc.getDate("recordedDate"));			
		}
		
		if(doc.containsKey("recorder")){
			ReferenceMap map = new ReferenceMap();
			Reference obj = map.mapToFhir((Document)doc.get("recorder"));
			ret.setRecorder(obj);									
		}
		
		if(doc.containsKey("asserter")){
			ReferenceMap map = new ReferenceMap();
			Reference obj = map.mapToFhir((Document)doc.get("asserter"));
			ret.setAsserter(obj);								
		}
		
		if(doc.containsKey("stage")){
			ConditionStageComponentMap map = new ConditionStageComponentMap();
			@SuppressWarnings("unchecked")
			List<ConditionStageComponent> obj = map.mapToFhir((List<Document>)doc.get("stage"));
			ret.setStage(obj);						
		}
		
		if(doc.containsKey("evidence")){
			ConditionEvidenceComponentMap map = new ConditionEvidenceComponentMap();
			@SuppressWarnings("unchecked")
			List<ConditionEvidenceComponent> obj = map.mapToFhir((List<Document>)doc.get("evidence"));
			ret.setEvidence(obj);	
		}
												
		return ret;						
	}
}

class ConditionStageComponentMap{
	
	public List<Document> map(List<ConditionStageComponent> stages)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(ConditionStageComponent csc: stages){
			Document bdo = map(csc);
			retList.add(bdo);
		}
		
		return retList;
	}
	
	public List<ConditionStageComponent> mapToFhir(List<Document> docs){
		
		List<ConditionStageComponent> retList = new ArrayList<ConditionStageComponent>(0);
		
		for(Document doc: docs){
			ConditionStageComponent bdo = mapToFhir(doc);
			retList.add(bdo);
		}
		
		return retList;
	}
		
	public Document map(ConditionStageComponent stage) {
		
		Document  docDiag = new Document ();
		
		if(stage.hasSummary())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(stage.getSummary());
			docDiag.put("summary", bd);			
		}
		
		if(stage.hasAssessment())
		{
			ReferenceMap map = new ReferenceMap();
			List<Document> bd = map.map(stage.getAssessment());
			docDiag.put("assessment", bd);				
		}
		
		if(stage.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(stage.getType());
			docDiag.put("type", bd);			
		}
						
		return docDiag;					
	}	
	
	public ConditionStageComponent mapToFhir(Document doc) {
		
		ConditionStageComponent  ret = new ConditionStageComponent ();
		
		if(doc.containsKey("summary")){
			CodeableConceptMap map = new CodeableConceptMap();
			CodeableConcept obj = map.mapToFhir((Document)doc.get("summary"));
			ret.setSummary(obj);		
		}
		
		if(doc.containsKey("assessment")){
			ReferenceMap map = new ReferenceMap();
			@SuppressWarnings("unchecked")
			List<Reference> obj = map.mapToFhir((List<Document>)doc.get("summary"));
			ret.setAssessment(obj);			
		}
		
		if(doc.containsKey("type")){
			CodeableConceptMap map = new CodeableConceptMap();
			CodeableConcept obj = map.mapToFhir((Document)doc.get("type"));
			ret.setType(obj);			
		}
						
		return ret;					
	}
}

class ConditionEvidenceComponentMap{
	
	public List<Document> map(List<ConditionEvidenceComponent> eves)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(ConditionEvidenceComponent eve: eves){
			Document bdo = map(eve);
			retList.add(bdo);
		}
				
		return retList;
	}
	
	public List<ConditionEvidenceComponent> mapToFhir(List<Document> docs)
	{
		List<ConditionEvidenceComponent> retList = new ArrayList<ConditionEvidenceComponent>(0);
		
		for(Document doc: docs){
			ConditionEvidenceComponent bdo = mapToFhir(doc);
			retList.add(bdo);
		}
				
		return retList;
	}
		
	public Document map(ConditionEvidenceComponent eve) {
		
		Document  docEve = new Document ();
		
		if(eve.hasCode()){
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bd = map.map(eve.getCode());
			docEve.put("code", bd);	
		}
		
		if(eve.hasDetail()){
			ReferenceMap map = new ReferenceMap();
			List<Document> bd = map.map(eve.getDetail());
			docEve.put("detail", bd);						
		}
							
		return docEve;			
	}	
	
	public ConditionEvidenceComponent mapToFhir(Document doc) {
		
		ConditionEvidenceComponent  ret = new ConditionEvidenceComponent ();
		
		if(doc.containsKey("code")){
			CodeableConceptMap map = new CodeableConceptMap();
			@SuppressWarnings("unchecked")
			List<CodeableConcept> obj = map.mapToFhir((List<Document>)doc.get("code"));
			ret.setCode(obj);
		}
		
		if(doc.containsKey("detail")){
			ReferenceMap map = new ReferenceMap();
			@SuppressWarnings("unchecked")
			List<Reference> obj = map.mapToFhir((List<Document>)doc.get("detail"));
			ret.setDetail(obj);	
		}
								
		return ret;
	}	
}





