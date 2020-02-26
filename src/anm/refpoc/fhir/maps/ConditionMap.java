package anm.refpoc.fhir.maps;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DomainResource;

public class ConditionMap extends BaseMap{	
	
	private static  ConditionMap _instance;
	
	public static ConditionMap getInstance()
	{
		if(_instance == null)
		{
			_instance = new ConditionMap();
		}
		
		return _instance;
	}
	
	private ConditionMap()
	{
		super("Condition");			
	}
	
	public List<Condition> mapToFhir(ArrayList<Object> docConditions)
	{
		List<Condition> rets = new ArrayList<Condition>(0);
		
		for(int i = 0; i < docConditions.size(); i++)
		{
			Condition pat = (Condition)this.mapToFhir(docConditions.get(i));
			rets.add(pat);
		}		
		return rets;		
	}	
	
	protected DomainResource instantiateResource()
	{
		return new Condition();
	}
}