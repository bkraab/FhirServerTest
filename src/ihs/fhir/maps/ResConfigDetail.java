package ihs.fhir.maps;


public class ResConfigDetail {
	protected String _resName;	
	protected int _sequence;
	protected String _key;
	protected String _hasMethod;
	protected String _getMethod;
	protected String _setMethod;
	protected String _subResName;	
	protected ResConfig _subResConfig;
	
	public ResConfigDetail()
	{
		
	}
	
	public ResConfigDetail(String key)
	{
		this._key = key;		
		this._hasMethod = fixMethodName("has", key);
		this._getMethod = fixMethodName("get", key);
		this._setMethod = fixMethodName("set", key);				
	}
	
	
	public String getResName()
	{
		return this._resName;
	}
	
	public void setResName(String type)
	{
		this._resName = type;
	}
	
	public int getSequence()
	{
		return this._sequence;
	}
	
	public void setSequence(int seq)
	{
		this._sequence = seq;
	}
	
	public String getKey()
	{
		return this._key;
	}
	
	public void setKey(String key)
	{
		this._key = key;
	}
	
	public String getHasMethod()
	{
		return this._hasMethod;
	}
	
	public void setHasMethod(String meth)
	{
		this._hasMethod = meth;
	}
	
	public String getGetMethod()
	{
		return this._getMethod;
	}
	
	public void setGetMethod(String meth)
	{
		this._getMethod = meth;
	}
		
	public String getSetMethod()
	{
		return this._setMethod;
	}
	
	public void setSetMethod(String meth)
	{
		this._setMethod = meth;
	}
	
	public String getSubResName()
	{
		return this._subResName;
	}
	
	public void setSubResName(String subName)
	{
		this._subResName = subName;
	}
	
	public ResConfig getSubResConfig()
	{
		return this._subResConfig;
	}
	
	public void setSubResConfig(ResConfig resConfig)
	{
		this._subResConfig = resConfig;
	}
			
	protected String fixMethodName(String pre, String key)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(pre);
		sb.append(key.substring(0,1).toUpperCase());
		sb.append(key.substring(1));
		return sb.toString();
	}
}
