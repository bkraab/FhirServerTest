package ihs.fhir.services;

import java.util.List;

import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Patient.ContactComponent;

public class PatientConstraints {

	public void enforce(Patient pat)
	{
		// Patient.Contact - SHALL at least contain a contact's details or a reference to an organization
		// If any do not exists, remove the contact Object from the
		if(pat.hasContact() == false)
			return;
		
		// Lets see if there is a name on the contact.  If so, we are good so we can return.
		List<ContactComponent> cons = pat.getContact();
		
		for(int i = 0; i < cons.size(); i++)
		{
			if(isValid(cons.get(i)) == false)
			{
				cons.remove(i);
				i--;
			}
					
		}						
	}
	
	
	private boolean isValid(ContactComponent contact)
	{
		// IF at least one required field is there we can return true;
		
		if(contact.hasName())
			return true;
		
		if(contact.hasTelecom())
			return true;
		
		if(contact.hasAddress())
			return true;
		
		return false;
	}
}
