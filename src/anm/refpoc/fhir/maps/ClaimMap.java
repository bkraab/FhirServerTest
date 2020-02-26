package anm.refpoc.fhir.maps;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.Claim;
import org.hl7.fhir.r4.model.DomainResource;

public class ClaimMap extends BaseMap{	
	
	private static  ClaimMap _instance;
	
	public static ClaimMap getInstance()
	{
		if(_instance == null)
		{
			_instance = new ClaimMap();
		}		
		return _instance;
	}
	
	private ClaimMap()
	{
		super("Claim");				
	}	
		
	public List<Claim> mapToFhir(ArrayList<Object> docClaims)
	{
		List<Claim> rets = new ArrayList<Claim>(0);
		
		for(int i = 0; i < docClaims.size(); i++)
		{
			Claim pat = (Claim)this.mapToFhir(docClaims.get(i));
			rets.add(pat);
		}
		
		return rets;		
	}		

	protected DomainResource instantiateResource()
	{
		return new Claim();
	}
}