package anm.refpoc.fhir.repos.maps.mongo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.StringType;

import anm.refpoc.fhir.maps.ResConfig;
import anm.refpoc.fhir.maps.ResConfigDetail;

public class MongoRecursion {

	public void convertFromFhir(List<ResConfigDetail> resConfigs, Object source, Document target)
	{
		try
		{
			HumanName hn = new HumanName();
			
			Class<?> sourceClass = source.getClass();
			
			for(int i = 0; i < resConfigs.size(); i++)
			{
				try
				{
					ResConfigDetail detail = resConfigs.get(i);
					
					// Check for a Has method. If one exists, and it is false, contiue the loop.
					
					Method hasMeth = sourceClass.getMethod(detail.getHasMethod());
					if(hasMeth != null)
					{
						Boolean b = (Boolean)hasMeth.invoke(source);				
						if(b == false)
							continue;;
					}
					
					// Use the Get method to Get the actual value.
					Method getMeth = sourceClass.getMethod(detail.getGetMethod());
					Object getVal = getMeth.invoke(source);
					
					if(getVal instanceof List)
					{
						ResConfig rs = detail.getSubResConfig();
						List<ResConfigDetail> subList = rs.getResDetails();
						
						//List<MapMeta> subMeta = detail.get.getSubList();
						@SuppressWarnings("unchecked")
						List<Object> subSources = (List<Object>)getVal;
						List<Document> newTargs = new ArrayList<Document>(0);					
						target.put(detail.getKey(), newTargs);
						
						for(int j = 0; j < subSources.size(); j++) 
						{
							Document t = new Document();
							newTargs.add(t);
							convertFromFhir(subList, subSources.get(j), t);
						}
						continue;
					}
					
					if(detail.getSubResConfig() != null)
					{
						// This must be an object needing mapping.
						Document t = new Document();
						target.put(detail.getKey(), t);
						
						ResConfig rs = detail.getSubResConfig();						
						convertFromFhir(rs.getResDetails(), getVal, t);
						continue;
					}
					
					if(getVal instanceof BooleanType)
					{
						boolean b = ((BooleanType) getVal).getValue();
						target.put(detail.getKey(), b);
					}
					else if(getVal instanceof DateTimeType)
					{
						Date d = ((DateTimeType) getVal).getValue();
						target.put(detail.getKey(), d);
					}
					else if(getVal instanceof BigDecimal)
					{
						BigDecimal bd = (BigDecimal)getVal;
						int bigi = bd.intValue();
						target.put(detail.getKey(), bigi);
					}
					else if(getVal instanceof StringType)
					{
						StringType st  = (StringType)getVal;
						String sv = st.asStringValue();
						target.put(detail.getKey(), sv);
					}
					else if(getVal instanceof Enum)
					{
						String v = getVal.toString();
						target.put(detail.getKey(), v);
					}
					else if(getVal instanceof Date)
					{
						try
						{
							Date d = (Date)getVal;							
							target.put(detail.getKey(), d);
						}
						catch(Exception e)
						{
							
						}												
					}
					else
					{
						target.put(detail.getKey(), getVal);
					}
				}
				catch(NoSuchMethodException e)
				{
					e.printStackTrace();
				}
								
			}			
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}	
	
	
	public void convertToFhir(List<ResConfigDetail> resConfigs, Document source, Object target)
	{
		String resourceName = null;
		String setMethod = null;
		
		try
		{
			for(int i = 0; i < resConfigs.size(); i++)
			{
				
				try
				{
					// See if there is data for this key.  If not, continue to the next key.
					
					ResConfigDetail detail = resConfigs.get(i);
					
					resourceName = detail.getResName();
					setMethod = detail.getSetMethod();
//					System.out.println("Resource is: " + resourceName + " / SetMethod: " + setMethod);
					Object sourceData = source.get(detail.getKey());
					
					if( (sourceData == null) && ( (sourceData instanceof Boolean) == false ))
					{
						continue;
					}
					
					Method targetMethod = getMethod(target, detail.getSetMethod());
					
					if(sourceData instanceof ArrayList)
					{	
						List<Object> newTargList = new ArrayList<Object>(0);
						
						//BasicDBList sourceList = (BasicDBList)sourceData;
						for(int x = 0; x < ((ArrayList<?>)sourceData).size(); x++)
						{
							ResConfig config = detail.getSubResConfig();														
							Object newTarg = Class.forName(config.getClassName()).newInstance();							
							newTargList.add(newTarg);
							Document newSource = (Document)((ArrayList<?>)sourceData).get(x);
							List<ResConfigDetail> childConfigs = config.getResDetails();													
							convertToFhir(childConfigs, newSource, newTarg);
						}
						targetMethod.invoke(target, newTargList);
						continue;
					}
					
					// if this key has subkeys then this will need to be called recursively.
					if(detail.getSubResConfig()!= null)
					{
						ResConfig rs = detail.getSubResConfig();
						
						
						Object newTarg = Class.forName(rs.getClassName()).newInstance();
						targetMethod.invoke(target, newTarg);
						// This must be an object needing mapping.
						convertToFhir(rs.getResDetails(), (Document)sourceData, newTarg);						
						continue;
					}
					
					Class<?>[] pType  = targetMethod.getParameterTypes();
															
					if(pType[0].isEnum())
					{
						setEnum(pType[0], targetMethod, sourceData, target);
						continue;						
					}
					
					if(pType[0].isInstance(new Date()))
					{
						targetMethod.invoke(target, (Date)sourceData);
						continue;
					}
					
					targetMethod.invoke(target, sourceData);

				}
				catch(Exception e)
				{
					System.out.println("Resource is: " + resourceName + " / SetMethod: " + setMethod);
					e.printStackTrace();
				}
								
			}			
		}		
		catch(Exception e)
		{
			System.out.println("Resource is: " + resourceName + " / SetMethod: " + setMethod);
			e.printStackTrace();
		}
		
	}
	
	
	private Method getMethod(Object target, String methName)
	{
		Method retMeth = null;
		try
		{		
			Class<?> targetClass = target.getClass();
			Method [] allMethods = targetClass.getMethods();			
			for(Method meth :allMethods)
			{
				if(meth.getName().equals(methName))
				{							
					retMeth = meth;					
					break;
				}												
			}							
		}
		catch(Exception e)
		{
			
		}
		
				
		return retMeth;
	}
	
	private void setEnum(Class<?> pType, Method targetMethod, Object sourceData, Object target )
	{
		try
		{
			Object [] cons = pType.getEnumConstants();
			
			
			for(int i = 0; i < cons.length; i++)
			{
				
				String sc = cons[i].toString();
				
				if(sc.equalsIgnoreCase((String)sourceData))
				{
					targetMethod.invoke(target, cons[i]);
					break;
				}
			}
		}
		catch(InvocationTargetException e)
		{
			
		}
		catch(IllegalAccessException e)
		{
			
		}
	}
}

