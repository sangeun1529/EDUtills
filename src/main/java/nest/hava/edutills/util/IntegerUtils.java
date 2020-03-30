package nest.hava.edutills.util;

import java.util.List;

public class IntegerUtils {
	
	
	public static Integer convertToInteger(Object o)
	{
		if(o instanceof Integer)
		{
			return ((Integer) o).intValue();
			
		}else if(o instanceof Long)
		{
			return ((Long) o).intValue();
			
		}else if(o instanceof Double)
		{
			return ((Double) o).intValue();
			
		}else if(o instanceof String)
		{
			String tt = (String)o;
			tt = StringUtils.remove(tt);
			return Integer.parseInt(tt);
		}else {
			return (Integer) o;
		}
	}
	
	
	
	public static double [] convertToDoubleArray(List<Integer> listArray)
	{
		double [] dArray = new double[listArray.size()];
		
		for(int i=0; i< listArray.size() ; i++)
		{
			dArray[i] = listArray.get(i);
		}
		
		return dArray;
	}
	
	
}
