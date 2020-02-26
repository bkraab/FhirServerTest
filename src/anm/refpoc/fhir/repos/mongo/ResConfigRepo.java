package anm.refpoc.fhir.repos.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import anm.refpoc.fhir.maps.ResConfig;
import anm.refpoc.fhir.maps.ResConfigDetail;


public class ResConfigRepo extends MongoBaseRepo 
{
	public ResConfigRepo() {
		
	}
		
	public ResConfig findByResName(String name) {
		
		Document query = new Document();
		query.put("resName", name);
		query.put("isActive", "TRUE");
		
		Document obj = this.readInstance("ResConfig", query);
		
		if(obj == null)
			return null;
		
		ResConfig rs = new ResConfig();
		String rn = obj.getString("resName");
		String cn = obj.getString("className");
		rs.setClassName(cn);
		rs.setResName(rn);	
		
		List<ResConfigDetail> details = loadDetails(name);
		rs.setResDetails(details);
		
		return rs;				
	}
	
	public List<ResConfigDetail> loadDetails(String resName)
	{
		Document query = new Document();
		query.put("resName", resName);
		query.put("isActive", "TRUE");
		
		Document sort = new Document();
		sort.put("sequence",1);
		
		List<Document> details = this.readAllSort("ResConfigDetail", query, sort);	
		
		List<ResConfigDetail> rets = new ArrayList<ResConfigDetail>(0);
		for(int i = 0; i < details.size(); i++)
		{
			Document bdo = (Document)details.get(i);
			
			String rName = bdo.getString("resName");
			
			String key = bdo.getString("key");
			String subResName = bdo.getString("subResName");
			String hasMeth = bdo.getString("hasMethodName");
			String getMeth = bdo.getString("getMethodName");
			String setMeth = bdo.getString("setMethodName");
			Integer iseq =  bdo.getInteger("sequence");
			//Integer iseq = Integer.parseInt(sseq);
			
			ResConfigDetail detail = new ResConfigDetail(key);
			rets.add(detail);
			detail.setResName(rName);					
			detail.setSubResName(subResName);						
			detail.setSequence(iseq);				
															
			if(hasMeth != null && hasMeth.length() > 1)
			{
				detail.setHasMethod(hasMeth);
			}
			
			if(getMeth != null && getMeth.length() > 1)
			{
				detail.setGetMethod(getMeth);
			}
			
			if(setMeth != null && setMeth.length() > 1)
			{
				detail.setSetMethod(setMeth);
			}
			
			if(subResName != null && subResName.length() > 0)
			{
				ResConfig child = findByResName(subResName);
				detail.setSubResConfig(child);				
			}								
		}
		
		return rets;
						
	}

}