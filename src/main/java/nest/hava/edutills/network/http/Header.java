package nest.hava.edutills.network.http;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Header {
	
	Map<String,String> param = new HashMap<>();
	
	public void addParam(String key, String value)
	{
		param.put(key, value);
	}
	
	
	public String getValue(String key)
	{
		return param.get(key);
	}
	
	
	public List<String> getKeyList()
	{
		List<String> keyList = new ArrayList<>();
		param.keySet().forEach(k -> keyList.add(k));
		
		return keyList;
	}

	public Header copy()
	{
		Header tt = new Header();
		
		param.keySet().forEach(x -> tt.addParam(x, param.get(x)));
		
		return tt;
		
	}
}
