package ihs.fhir.services;

import java.util.List;

import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.IdType;

import ihs.fhir.repos.mongo.ProcedureRepoMongo;

public class ProcedureService {
	
	public String createProcedure(Procedure procedure)
	{	
		ProcedureRepoMongo repo = new ProcedureRepoMongo();
		String id = repo.insertCollection(procedure);		
		return id;		
	}
	
	public void updateProcedure(IdType theId, Procedure procedure)
	{
		ProcedureRepoMongo repo = new ProcedureRepoMongo();
		repo.updateProcedure(theId, procedure);		
		
	}
	
	public void deleteProcedure(IdType theId)
	{
		ProcedureRepoMongo repo = new ProcedureRepoMongo();
		repo.deleteDocument2(theId.getIdPart());		
	}
	
	public Procedure findById(IdType id)
	{
		ProcedureRepoMongo repo = new ProcedureRepoMongo();
		Procedure pro = (Procedure)repo.findById(id);			
		return pro;
	}
	
	public List<DomainResource> findAll()
	{
		ProcedureRepoMongo repo = new ProcedureRepoMongo();
		List<DomainResource> ais = repo.findAll();		
		return ais;
	}
}
