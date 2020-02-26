package anm.refpoc.fhir.maps;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.AllergyIntolerance;

public class AllergyIntoleranceMap extends BaseMap {	
	
	private static  AllergyIntoleranceMap _instance;
	
	public static AllergyIntoleranceMap getInstance()
	{
		if(_instance == null)
		{
			_instance = new AllergyIntoleranceMap();
		}
		
		return _instance;
	}
		
	private AllergyIntoleranceMap()
	{	
		super("AllergyIntolerance");								
	}
			
	public List<AllergyIntolerance> mapToFhir(ArrayList<Object> documentAllergyIntolerances)
	{
		List<AllergyIntolerance> rets = new ArrayList<AllergyIntolerance>(0);
		
		for(int i = 0; i < documentAllergyIntolerances.size(); i++)
		{
			AllergyIntolerance pat = (AllergyIntolerance)this.mapToFhir(documentAllergyIntolerances.get(i));
			rets.add(pat);
		}
		
		return rets;		
	}
	
	protected DomainResource instantiateResource()
	{
		return new AllergyIntolerance();
	}
}

