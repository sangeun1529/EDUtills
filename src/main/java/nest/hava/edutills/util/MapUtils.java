package nest.hava.edutills.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;


public class MapUtils {
	
	
	public static <K, V> Map<K, V> makeConcurrentHashMap()
	{
		return new ConcurrentHashMap<K, V>();
	}

	public static <X, Y, Z> Map<X, Z> transform(Map<? extends X, ? extends Y> input, Function<Y, Z> function) {
		return input.keySet().stream()
				.collect(Collectors.toMap(Function.identity(), key -> function.apply(input.get(key))));
	}
	
	public static <X, Y> Map<X, Y> copy(Map<? extends X, ? extends Y> input) {
		return input.keySet().stream()
				.collect(Collectors.toMap(Function.identity(), key -> input.get(key)));
	}
	
	public static <X, Y> Map<X, Y> copy(Map<? extends X, ? extends Y> input,X newKey, Y value) {
		Map<X,Y> cMap = input.keySet().stream()
						.collect(Collectors.toMap(Function.identity(), key -> input.get(key)));
		
		cMap.put(newKey, value);
		
		return cMap;
	}
	
	public static <X, Y> Map<X, Y> copyWithout(Map<? extends X, ? extends Y> input,X newKey) {
		Map<X,Y> cMap = input.keySet().stream()
						.collect(Collectors.toMap(Function.identity(), key -> input.get(key)));
		
		cMap.remove(newKey);
		
		return cMap;
	}
	
	public static <X, Y> Map<X, Y> copyWithout(Map<? extends X, ? extends Y> input,X[] newKeys) {
		Map<X,Y> cMap = input.keySet().stream()
						.collect(Collectors.toMap(Function.identity(), key -> input.get(key)));
		
		if(newKeys!=null)
		{
			for(X newKey: newKeys)
			{
				cMap.remove(newKey);
			}
			
		}
		
		return cMap;
	}
	

}
