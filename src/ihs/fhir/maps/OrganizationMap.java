package ihs.fhir.maps;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.Organization;

public class OrganizationMap extends BaseMap {	
	
	private static OrganizationMap _instance;
	
	public static OrganizationMap getInstance()
	{
		if(_instance == null)
		{
			_instance = new OrganizationMap();
		}
		
		return _instance;
	}
	
	private OrganizationMap()
	{
		super("Organization");				
	}
	
	public List<Organization> mapToFhir(ArrayList<Object> fromDocOrgs)
	{
		List<Organization> rets = new ArrayList<Organization>(0);
		
		for(int i = 0; i < fromDocOrgs.size(); i++)
		{
			Organization enc = (Organization)this.mapToFhir(fromDocOrgs.get(i));
			rets.add(enc);
		}
		
		return rets;		
	}	
	
	protected DomainResource instantiateResource()
	{
		return new Organization();
	}
}


