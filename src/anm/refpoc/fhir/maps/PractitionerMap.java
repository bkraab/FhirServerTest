package anm.refpoc.fhir.maps;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.Practitioner;

public class PractitionerMap extends BaseMap {	
	
	private static  PractitionerMap _instance;
	
	public static PractitionerMap getInstance()
	{
		if(_instance == null)
		{
			_instance = new PractitionerMap();
		}
		
		return _instance;
	}
		
	private PractitionerMap()
	{	
		super("Practitioner");								
	}
			
	public List<Practitioner> mapToFhir(ArrayList<Object> documentPractitioners)
	{
		List<Practitioner> rets = new ArrayList<Practitioner>(0);
		
		for(int i = 0; i < documentPractitioners.size(); i++)
		{
			Practitioner pat = (Practitioner)this.mapToFhir(documentPractitioners.get(i));
			rets.add(pat);
		}
		
		return rets;		
	}
	
	protected DomainResource instantiateResource()
	{
		return new Practitioner();
	}
}

