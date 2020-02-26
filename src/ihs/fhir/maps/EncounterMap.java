package ihs.fhir.maps;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.Encounter;

public class EncounterMap extends BaseMap {	
	
	private static EncounterMap _instance;
	
	public static EncounterMap getInstance()
	{
		if(_instance == null)
		{
			_instance = new EncounterMap();
		}
		
		return _instance;
	}
		
	private EncounterMap()
	{
		super("Encounter");								
	}
	
	public List<Encounter> mapToFhir(ArrayList<Object> documentEncounters)
	{
		List<Encounter> rets = new ArrayList<Encounter>(0);
		
		for(int i = 0; i < documentEncounters.size(); i++)
		{
			Encounter enc = (Encounter)this.mapToFhir(documentEncounters.get(i));
			rets.add(enc);
		}
		
		return rets;		
	}	
	
	protected DomainResource instantiateResource()
	{
		return new Encounter();
	}
}


