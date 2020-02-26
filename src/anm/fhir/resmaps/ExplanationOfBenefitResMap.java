package anm.fhir.resmaps;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.ExplanationOfBenefit;
import org.hl7.fhir.r4.model.ExplanationOfBenefit.CareTeamComponent;
import org.hl7.fhir.r4.model.ExplanationOfBenefit.DiagnosisComponent;
import org.hl7.fhir.r4.model.ExplanationOfBenefit.PayeeComponent;
import org.hl7.fhir.r4.model.ExplanationOfBenefit.ProcedureComponent;
import org.hl7.fhir.r4.model.ExplanationOfBenefit.RelatedClaimComponent;
import org.hl7.fhir.r4.model.ExplanationOfBenefit.SupportingInformationComponent;

import anm.fhir.maps.datatypes.*;

public class ExplanationOfBenefitResMap {
	
	public List<Document> map(List<ExplanationOfBenefit> oebs)
	{
		List<Document> ret = new ArrayList<Document>(0);
		
		for(ExplanationOfBenefit eob : oebs){
			Document bd = map(eob);
			ret.add(bd);
		}
		
		return ret;
	}
	
	public Document map(ExplanationOfBenefit eob) {
			
		Document  docEob = new Document ();
		
		if(eob.hasIdentifier())
		{
			IdentifierMap map = new IdentifierMap();
			List<Document> bd = map.map(eob.getIdentifier());
			docEob.put("identifier", bd);
		}
		
		if(eob.hasStatus())
		{
			docEob.put("status", eob.getStatus().toString());							
		}
		
		if(eob.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(eob.getType());
			docEob.put("type", bd);							
		}
		
		if(eob.hasSubType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(eob.getSubType());
			docEob.put("subType", bd);							
		}
		
		if(eob.hasUse())
		{
			docEob.put("use", eob.getUse().toString());							
		}
		
		if(eob.hasPatient())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(eob.getPatient());
			docEob.put("patient", bd);							
		}
		
		if(eob.hasBillablePeriod())
		{
			PeriodMap map = new PeriodMap();
			Document bd = map.map(eob.getBillablePeriod());
			docEob.put("billablePeriod", bd);							
		}
		
		if(eob.hasSubType())
		{
			docEob.put("created", eob.getCreated());							
		}
		
		if(eob.hasEnterer())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(eob.getEnterer());
			docEob.put("enterer", bd);							
		}
		
		if(eob.hasInsurer())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(eob.getInsurer());
			docEob.put("insurer", bd);							
		}
		
		if(eob.hasProvider())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(eob.getProvider());
			docEob.put("provider", bd);							
		}
		
		if(eob.hasPriority())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(eob.getPriority());
			docEob.put("priority", bd);							
		}
		
		if(eob.hasFundsReserveRequested())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(eob.getFundsReserveRequested());
			docEob.put("fundsReserveRequested", bd);							
		}
		
		if(eob.hasFundsReserve())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(eob.getFundsReserve());
			docEob.put("fundsReserve", bd);							
		}
		
		if(eob.hasRelated())
		{
			RelatedClaimComponentMap2 map = new RelatedClaimComponentMap2();
			List<Document> bd = map.map(eob.getRelated());
			docEob.put("related", bd);							
		}
		
		if(eob.hasPrescription())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(eob.getPrescription());
			docEob.put("prescription", bd);							
		}
		
		if(eob.hasOriginalPrescription())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(eob.getOriginalPrescription());
			docEob.put("originalPrescription", bd);							
		}
		
		if(eob.hasPayee())
		{
			PayeeComponentMap2 map = new PayeeComponentMap2();
			Document bd = map.map(eob.getPayee());
			docEob.put("payee", bd);							
		}
		
		
		if(eob.hasReferral())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(eob.getReferral());
			docEob.put("referral", bd);							
		}
		
		if(eob.hasFacility())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(eob.getFacility());
			docEob.put("facility", bd);							
		}
		
		if(eob.hasClaim())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(eob.getClaim());
			docEob.put("claim", bd);							
		}
		
		if(eob.hasClaimResponse())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(eob.getClaimResponse());
			docEob.put("claimResponse", bd);							
		}
		
		if(eob.hasOutcome())
		{
			docEob.put("outcome", eob.getOutcome().toString());							
		}
		
		
		if(eob.hasDisposition())
		{
			docEob.put("disposition", eob.getDisposition());							
		}
		
		if(eob.hasPreAuthRef())
		{
			//TODO: Attnetion Needed
			//docEob.put("preAuthRef", eob.getPreAuthRef());							
		}
		
		if(eob.hasPreAuthRefPeriod())
		{
			PeriodMap map = new PeriodMap();
			List<Document> bd = map.map(eob.getPreAuthRefPeriod());
			docEob.put("preAuthRefPeriod", bd);							
		}
		
		if(eob.hasCareTeam())
		{
			CareTeamComponentMap2 map = new CareTeamComponentMap2();
			List<Document> bd = map.map(eob.getCareTeam());
			docEob.put("careTeam", bd);							
		}
		
		if(eob.hasSupportingInfo())
		{
			SupportingInformationComponentMap2 map = new SupportingInformationComponentMap2();
			List<Document> bd = map.map(eob.getSupportingInfo());
			docEob.put("supportingInfo", bd);							
		}
		
		if(eob.hasDiagnosis())
		{
			DiagnosisComponentMap2 map = new DiagnosisComponentMap2();
			List<Document> bd = map.map(eob.getDiagnosis());
			docEob.put("diagnosis", bd);
										
		}
		
		if(eob.hasProcedure())
		{
			ProcedureComponentMap2 map = new ProcedureComponentMap2();
			List<Document> bd = map.map(eob.getProcedure());
			docEob.put("procedure", bd);
		}
		
		if(eob.hasPrecedence())
		{
			docEob.put("precedence", eob.getPrecedence());							
		}
		
		if(eob.hasInsurance())
		{
										
		}
		
		if(eob.hasAccident())
		{
										
		}
		
		if(eob.hasItem())
		{
										
		}
		
		
		
		
		
														
		return docEob;						
	}	
}

class RelatedClaimComponentMap2{
	
	public List<Document> map(List<RelatedClaimComponent> coms)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(RelatedClaimComponent stage: coms){
			Document bdo = map(stage);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(RelatedClaimComponent com) {
		
		Document  doc = new Document ();
		
		if(com.hasClaim())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(com.getClaim());
			doc.put("claim", bd);							
		}
		
		if(com.hasRelationship())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(com.getRelationship());
			doc.put("relationship", bd);							
		}
		
		if(com.hasReference())
		{
			IdentifierMap map = new IdentifierMap();
			Document bd = map.map(com.getReference());
			doc.put("reference", bd);
		}
		
								
		return doc;					
	}	
}

class PayeeComponentMap2{
	
	public List<Document> map(List<PayeeComponent> coms)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(PayeeComponent stage: coms){
			Document bdo = map(stage);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(PayeeComponent com) {
		
		Document  doc = new Document ();
		
		if(com.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(com.getType());
			doc.put("type", bd);							
		}
		
		if(com.hasParty())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(com.getParty());
			doc.put("party", bd);
		}
		
								
		return doc;					
	}	
}

class CareTeamComponentMap2{
	
	public List<Document> map(List<CareTeamComponent> coms)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(CareTeamComponent stage: coms){
			Document bdo = map(stage);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(CareTeamComponent com) {
		
		Document  doc = new Document ();
		
		if(com.hasSequence())
		{
			doc.put("sequence", com.getSequence());							
		}
		
		if(com.hasProvider())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(com.getProvider());
			doc.put("provider", bd);
		}
		
		if(com.hasResponsible())
		{
			doc.put("responsible", com.getResponsible());							
		}
		
		if(com.hasRole())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(com.getRole());
			doc.put("role", bd);	
										
		}
		
		if(com.hasQualification())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(com.getQualification());
			doc.put("qualification", bd);										
		}
														
		return doc;					
	}	
}


class SupportingInformationComponentMap2{
	
	public List<Document> map(List<SupportingInformationComponent> coms)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(SupportingInformationComponent stage: coms){
			Document bdo = map(stage);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(SupportingInformationComponent com) {
		
		Document  doc = new Document ();
		
		if(com.hasSequence())
		{
			doc.put("sequence", com.getSequence());							
		}
		
		if(com.hasCategory())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(com.getCategory());
			doc.put("category", bd);						
		}
		
		if(com.hasCode())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(com.getCode());
			doc.put("code", bd);							
		}
		
		if(com.hasTimingDateType())
		{
			doc.put("timing", com.getTimingDateType());							
		}
		
		if(com.hasTimingPeriod())
		{
			PeriodMap map = new PeriodMap();
			Document bd = map.map(com.getTimingPeriod());
			doc.put("timing", bd);							
		}
		
		if(com.hasValue())
		{
			if(com.hasValueAttachment())
			{
				//TODO: Handle Attachements
			}
			
			if(com.hasValueBooleanType())
			{
				doc.put("value", com.getValueBooleanType().booleanValue());
			}
			
			if(com.hasValueQuantity())
			{
				QuantityMap map = new QuantityMap();
				Document bd = map.map(com.getValueQuantity());
				doc.put("value", bd);
			}
			
			if(com.hasValueReference())
			{
				ReferenceMap map = new ReferenceMap();
				Document bd = map.map(com.getValueReference());
				doc.put("value", bd);		
			}
			
			if(com.hasValueStringType())
			{
				doc.put("value", com.getValueStringType().toString());		
			}																	
		}
		
		if(com.hasReason())
		{
			doc.put("reason", com.getReason());							
		}
		
		
													
		return doc;					
	}	
}

class DiagnosisComponentMap2{
	
	public List<Document> map(List<DiagnosisComponent> coms)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(DiagnosisComponent stage: coms){
			Document bdo = map(stage);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(DiagnosisComponent com) {
		
		Document doc = new Document ();
		
		if(com.hasSequence())
		{
			doc.put("sequence", com.getSequence());							
		}
		
		if(com.hasDiagnosisCodeableConcept())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(com.getDiagnosisCodeableConcept());
			doc.put("diagnosis", bd);
		}
		
		if(com.hasDiagnosisReference())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(com.getDiagnosisReference());
			doc.put("diagnosis", bd);
		}
		
		if(com.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bd = map.map(com.getType());
			doc.put("type", bd);
		}
		
		if(com.hasOnAdmission())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(com.getOnAdmission());
			doc.put("onAdmission", bd);
		}
		
		if(com.hasPackageCode())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(com.getPackageCode());
			doc.put("packageCode", bd);
		}
															
		return doc;					
	}	
}

class ProcedureComponentMap2{
	
	public List<Document> map(List<ProcedureComponent> coms)
	{
		List<Document> retList = new ArrayList<Document>(0);
		
		for(ProcedureComponent stage: coms){
			Document bdo = map(stage);
			retList.add(bdo);
		}
		
		
		return retList;
	}
		
	public Document map(ProcedureComponent com) {
		
		Document  doc = new Document ();
		
		if(com.hasSequence())
		{
			doc.put("sequence", com.getSequence());							
		}
		
		if(com.hasType())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			List<Document> bd = map.map(com.getType());
			doc.put("type", bd);										
		}
		
		if(com.hasDate())
		{
			doc.put("date", com.getDate());
		}
		
		if(com.hasProcedureCodeableConcept())
		{
			CodeableConceptMap map = new CodeableConceptMap();
			Document bd = map.map(com.getProcedureCodeableConcept());
			doc.put("procedure", bd);							
		}
		
		if(com.hasProcedureReference())
		{
			ReferenceMap map = new ReferenceMap();
			Document bd = map.map(com.getProcedureReference());
			doc.put("procedure", bd);									
		}
		
		if(com.hasUdi())
		{
			ReferenceMap map = new ReferenceMap();
			List<Document> bd = map.map(com.getUdi());
			doc.put("udi", bd);									
		}
																
		return doc;					
	}	
}










