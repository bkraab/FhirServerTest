package ihs.fhir.maps;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.Patient;

public class PatientMap extends BaseMap {	
	
	private static  PatientMap _instance;
	
	public static PatientMap getInstance()
	{
		if(_instance == null)
		{
			_instance = new PatientMap();
		}
		
		return _instance;
	}
		
	private PatientMap()
	{	
		super("Patient");								
	}
			
	public List<Patient> mapToFhir(ArrayList<Object> documentPatients)
	{
		List<Patient> rets = new ArrayList<Patient>(0);
		
		for(int i = 0; i < documentPatients.size(); i++)
		{
			Patient pat = (Patient)this.mapToFhir(documentPatients.get(i));
			rets.add(pat);
		}
		
		return rets;		
	}
	
	protected DomainResource instantiateResource()
	{
		return new Patient();
	}
}
