package nest.hava.edutills.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SetUtils {
	
	
	public static <T> List<T> toList(Set<T> set )
	{
		List<T> tList = new ArrayList<>();
		set.forEach(x -> tList.add(x));
		
		return tList;
	}

}
