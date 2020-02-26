package ihs.fhir.repos.maps.mongo;

import java.math.BigDecimal;
import java.util.Date;

import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.Type;

import com.mongodb.BasicDBObject;

public class HelperUtility {

	public void getDataType(Object source,  BasicDBObject target, String key)
	{
		
		if(source instanceof Type)
		{
			if(source instanceof BooleanType)
			{
				boolean b = ((BooleanType) source).getValue();
				target.put(key, b);
			}
			else if(source instanceof DateTimeType)
			{
				Date d = ((DateTimeType) source).getValue();
				target.put(key, d);
			}
			else if(source instanceof BigDecimal)
			{
				BigDecimal bd = (BigDecimal)source;
				int bigi = bd.intValue();
				target.put(key, bigi);
			}
			else if(source instanceof StringType)
			{
				StringType st  = (StringType)source;
				String sv = st.asStringValue();
				target.put(key, sv);
			}
		}							
	}
}
