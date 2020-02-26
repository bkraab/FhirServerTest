package anm.fhir.resmaps;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Encounter.ClassHistoryComponent;
import org.hl7.fhir.r4.model.Encounter.DiagnosisComponent;
import org.hl7.fhir.r4.model.Encounter.EncounterHospitalizationComponent;
import org.hl7.fhir.r4.model.Encounter.EncounterParticipantComponent;
import org.hl7.fhir.r4.model.Encounter.StatusHistoryComponent;
import org.hl7.fhir.r4.model.Encounter.EncounterLocationComponent;

import anm.fhir.maps.datatypes.*;

public class EncounterResMap {
	
	public Document map(Encounter enc) {
		
		Document  docEnc = new Document ();
		
		if(enc.hasIdentifier())
		{
			IdentifierMap map = new IdentifierMap();
			List<Document> bdl = map.map(enc.getIdentifier());
			docEnc.put("identifier", bdl);
		}
		
		if(enc.hasStatus())
		{
			docEnc.put("status", enc.getStatus().toString());
		}
		
		if(enc.hasStatusHistory())
		{
			StatusHistoryComponentMap2 map = new StatusHistoryComponentMap2();
			List<Document> bd = map.map(enc.getStatusHistory());
			docEnc.put("statusHistory", bd);			
		}
		
		if(enc.hasClass_())
		{
			CodingMap map = new CodingMap();
			Document bdo = map.map(enc.getClass_());
			docEnc.put("class", bdo);			
		}
		
		if(enc.hasClassHistory())
		{
			ClassHistoryComponentMap2 map = new ClassHistoryComponentMap2();
			List<Document> bd = map.map(enc.getClassHistory());
			docEnc.put("classHistory", bd);	
			
			//docEnc.put("active", org.getActive());
		}
		
		if(enc.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bdl = map.map(enc.getType());
			docEnc.put("type", bdl);							
		}
		
		if(enc.hasServiceType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bdo = map.map(enc.getServiceType());
			docEnc.put("type", bdo);							
		}
		
		if(enc.hasPriority())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bdo = map.map(enc.getPriority());
			docEnc.put("priority", bdo);							
		}
		
		if(enc.hasSubject())
		{
			ReferenceMap map = new ReferenceMap();
			Document bdo = map.map(enc.getSubject());
			docEnc.put("subject", bdo);			
		}
		
		if(enc.hasEpisodeOfCare())
		{
			ReferenceMap map = new ReferenceMap();
			List<Document> bd = map.map(enc.getEpisodeOfCare());
			docEnc.put("subject", bd);
		}
		
		if(enc.hasBasedOn())
		{
			ReferenceMap map = new ReferenceMap();
			List<Document> bd = map.map(enc.getBasedOn());
			docEnc.put("basedOn", bd);
		}
		
		if(enc.hasParticipant())
		{
			ParticipantComponentMap2 map = new ParticipantComponentMap2();
			List<Document> bd = map.map(enc.getParticipant());
			docEnc.put("participant", bd);
		}
						
		if(enc.hasAppointment())
		{
			ReferenceMap map = new ReferenceMap();
			List<Document> bd = map.map(enc.getAppointment());
			docEnc.put("appointment", bd);
		}
		
		if(enc.hasPeriod())
		{
			PeriodMap map = new PeriodMap();
			Document bdo = map.map(enc.getPeriod());
			docEnc.put("period", bdo);
			
			//docEnc.put("active", org.getActive());
		}
		
		if(enc.hasLength())
		{
			//Duration dur = enc.getLength();
			
			//docEnc.put("active", org.getActive());
		}
		
		if(enc.hasReasonCode())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bdl = map.map(enc.getReasonCode());
			docEnc.put("reasonCode", bdl);
		}
		
		if(enc.hasReasonReference())
		{
			ReferenceMap map = new ReferenceMap();
			List<Document> bd = map.map(enc.getReasonReference());
			docEnc.put("reasonReference", bd);
		}
		
		if(enc.hasDiagnosis())
		{
			DiagnosisComponentComponentMap2 map = new DiagnosisComponentComponentMap2();
			List<Document> bd = map.map(enc.getDiagnosis());
			docEnc.put("diagnosis", bd);
			
		}
		
		if(enc.hasAccount())
		{
			ReferenceMap map = new ReferenceMap();
			List<Document> bd = map.map(enc.getAccount());
			docEnc.put("account", bd);
		}
		
		if(enc.hasHospitalization())
		{
			EncounterHospitalizationComponentMap2 map = new EncounterHospitalizationComponentMap2();
			Document bd = map.map(enc.getHospitalization());
			docEnc.put("hospitalization", bd);
			
		}
		
		if(enc.hasLocation())
		{
			EncounterLocationComponentMap2 map = new EncounterLocationComponentMap2();
			List<Document> bd = map.map(enc.getLocation());
			docEnc.put("location", bd);
			
		}
		
		if(enc.hasServiceProvider())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(enc.getServiceProvider());
			docEnc.put("ServiceProvider", bd);
		}
		
		if(enc.hasPartOf())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(enc.getPartOf());
			docEnc.put("partOf", bd);
		}
		
		
		return docEnc;
					
	}	
}

class StatusHistoryComponentMap2{
	
	public List<Document> map(List<StatusHistoryComponent> hists)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(StatusHistoryComponent hist: hists){
			Document bdo = map(hist);
			retList.add(bdo);
		}
		
		
		return retList;
	}
	
	
	public Document map(StatusHistoryComponent hist) {
		
		Document  docHist = new Document ();
		
		if(hist.hasStatus())
		{
			docHist.put("status", hist.getStatus());			
		}
		
		if(hist.hasPeriod())
		{
			PeriodMap map = new PeriodMap();
			Document bdo = map.map(hist.getPeriod());
			docHist.put("period", bdo);
			
			//docEnc.put("active", org.getActive());
		}
		
		return docHist;
					
	}
	
}

class ClassHistoryComponentMap2{
	
	public List<Document> map(List<ClassHistoryComponent> hists)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(ClassHistoryComponent hist: hists){
			Document bdo = map(hist);
			retList.add(bdo);
		}
		
		return retList;
	}
	
	
	public Document map(ClassHistoryComponent hist) {
		
		Document  docHist = new Document ();
		
		if(hist.hasClass_())
		{
			CodingMap map = new CodingMap();
			Document bdo = map.map(hist.getClass_());
			docHist.put("class", bdo);							
		}
		
		if(hist.hasPeriod())
		{
			PeriodMap map = new PeriodMap();
			Document bdo = map.map(hist.getPeriod());
			docHist.put("period", bdo);						
		}
		
		return docHist;
					
	}
	
}

class ParticipantComponentMap2{
	
	public List<Document> map(List<EncounterParticipantComponent> pars)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(EncounterParticipantComponent par: pars ){
			Document bdo = map(par);
			retList.add(bdo);
		}
		
		return retList;
	}
	
	
	public Document map(EncounterParticipantComponent par) {
		
		Document  docPar = new Document ();
		
		if(par.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap ();
			List<Document> bd = map.map(par.getType());
			docPar.put("type", bd);							
		}
		
		if(par.hasPeriod())
		{
			PeriodMap map = new PeriodMap();
			Document bdo = map.map(par.getPeriod());
			docPar.put("period", bdo);						
		}
		
		if(par.hasIndividual())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(par.getIndividual());
			docPar.put("individual", bd);
		}
		
		return docPar;
					
	}
	
}

class DiagnosisComponentComponentMap2{
	
	public List<Document> map(List<DiagnosisComponent> diags)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(DiagnosisComponent diag: diags){
			Document bdo = map(diag);
			retList.add(bdo);
		}
		
		
		return retList;
	}
	
	
	public Document map(DiagnosisComponent diag) {
		
		Document  docDiag = new Document ();
		
		if(diag.hasCondition())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(diag.getCondition());
			docDiag.put("condition", bd);							
		}
		
		if(diag.hasUse())
		{
			CodeableConceptMap map = new CodeableConceptMap ();
			Document bd = map.map(diag.getUse());
			docDiag.put("use", bd);							
		}
		
		if(diag.hasRank())
		{
			docDiag.put("rank", diag.getRank());
		}
						
		return docDiag;
					
	}
	
}

class EncounterHospitalizationComponentMap2{
	
	public List<Document> map(List<EncounterHospitalizationComponent> comps)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(EncounterHospitalizationComponent diag: comps){
			Document bdo = map(diag);
			retList.add(bdo);
		}
		
		
		return retList;
	}
	
	
	public Document map(EncounterHospitalizationComponent comp) {
		
		Document  docDiag = new Document ();
		
		if(comp.hasPreAdmissionIdentifier())
		{
			IdentifierMap map = new IdentifierMap();
			Document bd = map.map(comp.getPreAdmissionIdentifier());
			docDiag.put("preAdmissionIdentifier", bd);							
		}
		
		if(comp.hasOrigin())
		{
			ReferenceMap map = new ReferenceMap ();
			Document bd = map.map(comp.getOrigin());
			docDiag.put("origin", bd);							
		}
		
		if(comp.hasAdmitSource())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(comp.getAdmitSource());
			docDiag.put("admitSource", bd);
		}
		
		if(comp.hasReAdmission())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(comp.getReAdmission());
			docDiag.put("reAdmission", bd);
		}
		
		if(comp.hasDietPreference())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bd = map.map(comp.getDietPreference());
			docDiag.put("dietPreference", bd);					
		}
		
		if(comp.hasSpecialCourtesy())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bd = map.map(comp.getSpecialCourtesy());
			docDiag.put("specialCourtesy", bd);								
		}
		
		if(comp.hasSpecialArrangement())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bd = map.map(comp.getSpecialArrangement());
			docDiag.put("specialArrangement", bd);					
		}
		
		if(comp.hasDestination())
		{
			ReferenceMap map = new ReferenceMap ();
			Document bd = map.map(comp.getDestination());
			docDiag.put("destination", bd);				
		}
		
		if(comp.hasDischargeDisposition())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(comp.getDischargeDisposition());
			docDiag.put("dischargeDisposition", bd);				
		}
						
		return docDiag;
					
	}
	
}

class EncounterLocationComponentMap2{
	
	public List<Document> map(List<EncounterLocationComponent> locs)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(EncounterLocationComponent diag: locs){
			Document bdo = map(diag);
			retList.add(bdo);
		}
		
		
		return retList;
	}
	
	
	public Document map(EncounterLocationComponent loc) {
		
		Document  docDiag = new Document ();
		
		if(loc.hasLocation())
		{
			ReferenceMap map = new ReferenceMap ();
			Document bd = map.map(loc.getLocation());
			docDiag.put("location", bd);
		}
		
		if(loc.hasStatus())
		{
			docDiag.put("status", loc.getStatus());							
		}
		
		if(loc.hasPhysicalType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(loc.getPhysicalType());
			docDiag.put("physicalType", bd);				
		}
		
		if(loc.hasPeriod())
		{
			PeriodMap map = new PeriodMap();
			Document bd = map.map(loc.getPeriod());
			docDiag.put("period", bd);
		}
						
		return docDiag;
					
	}
	
}

