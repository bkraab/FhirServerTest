package anm.refpoc.fhir.maps;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.Procedure;

public class ProcedureMap extends BaseMap{	
	
	private static ProcedureMap _instance;
	
	public static ProcedureMap getInstance()
	{
		if(_instance == null)
		{
			_instance = new ProcedureMap();
		}
		
		return _instance;
	}
	
	private ProcedureMap()
	{		
		super("Procedure");		
	}
	
	public List<Procedure> mapToFhir(ArrayList<Object> docProcedures)
	{
		List<Procedure> rets = new ArrayList<Procedure>(0);
		
		for(int i = 0; i < docProcedures.size(); i++)
		{
			Procedure pat = (Procedure)this.mapToFhir(docProcedures.get(i));
			rets.add(pat);
		}
		
		return rets;		
	}	
	
	protected DomainResource instantiateResource()
	{
		return new Procedure();
	}
}