package ihs.fhir.maps;

import java.util.ArrayList;
import java.util.List;

public class ResConfig {

	protected String _resName;	
	protected String _className;
	protected List<ResConfigDetail> _resDetails = new ArrayList<ResConfigDetail>(0);
	
	public ResConfig()
	{
		
	}
	
	public String getResName()
	{
		return this._resName;
	}
	
	public void setResName(String name)
	{
		this._resName = name;
	}
	
	public String getClassName()
	{
		return this._className;
	}
	
	public void setClassName(String cname)
	{
		this._className = cname;
	}
	
	public List<ResConfigDetail> getResDetails()
	{
		return this._resDetails;
	}
	
	public void setResDetails(List<ResConfigDetail> details)
	{
		this._resDetails = details;
	}
	
	public void setBaseResources(List<ResConfigDetail> resDetails, List<ResConfigDetail> domainDetails)
	{
		this._resDetails.addAll(0, domainDetails);
		this._resDetails.addAll(0, resDetails);
	}
	
	
}
