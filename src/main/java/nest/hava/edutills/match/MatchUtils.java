package nest.hava.edutills.match;


import nest.hava.edutills.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MatchUtils {

	
	public static boolean partialMatch(String pattern,String key)
	{
		
		if(StringUtils.isNullOrEmpty(key))
		{
			return false;
		}
		
		Pattern p = Pattern.compile(pattern);
		Matcher m =  p.matcher(key);
	
		while(m.find())
		{
			return true;
		}
		
		return false;
	}
	
	public static boolean partialMatch(String pattern,String key, int flag)
	{
		
		if(StringUtils.isNullOrEmpty(key))
		{
			return false;
		}
		
		Pattern p = Pattern.compile(pattern, flag);
		Matcher m =  p.matcher(key);
	
		while(m.find())
		{
			return true;
		}
		
		return false;
	}
	
	
	public static String returnFirstMatch(String pattern,String key)
	{
		
		if(StringUtils.isNullOrEmpty(key))
		{
			return "";
		}
		
		Pattern p = Pattern.compile(pattern);
		Matcher m =  p.matcher(key);
	
		while(m.find())
		{
			key = m.group();
			
			return key;
		}
		
		return "";
	}
	
	public static String partialMatchAndRemove(String pattern,String key)
	{
		
		if(StringUtils.isNullOrEmpty(key))
		{
			return key;
		}
		
		Pattern p = Pattern.compile(pattern);
		Matcher m =  p.matcher(key);
	
		while(m.find())
		{
			return m.replaceAll("");
		}
		
		return key;
	}
	
	public static String partialMatchAndFirstRemove(String pattern,String key)
	{
		
		if(StringUtils.isNullOrEmpty(key))
		{
			return key;
		}
		
		Pattern p = Pattern.compile(pattern);
		Matcher m =  p.matcher(key);
	
		while(m.find())
		{
			return m.replaceFirst("");
		}
		
		return key;
	}
	
	public static boolean equalMatch(String source, String target)
	{
		if(source.equals(target)) return true;
		else return false;
				
	}
	
	public static boolean equalMatchInIgnoreCase(String source, String target)
	{
		if(source.equalsIgnoreCase(target)) return true;
		else return false;
				
	}
	
	public static boolean equalMatch(String source, List<String> target)
	{
		for(String a: target)
		{
			if(equalMatch(source,a))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean equalMatchInIgnoreCase (String source, List<String> target)
	{
		for(String a: target)
		{
			if(equalMatchInIgnoreCase(source,a))
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	public static boolean fullMatch(String pattern,String key)
	{
		
		if(StringUtils.isNullOrEmpty(key))
		{
			return false;
		}
		Pattern p = Pattern.compile(pattern);
		Matcher m =  p.matcher(key);
	
		while(m.matches())
		{
			return true;
		}
		
		return false;
	}
	
	
	public static List<String> parseGroup(String pattern,String key)
	{
		if(StringUtils.isNullOrEmpty(key))
		{
			return null;
		}
		
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(key);
		
		List<String> tt= new ArrayList<>();
		
		while (matcher.find()) {
		    int groupCnt = matcher.groupCount();
		    
		    for(int i=1 ; i <= groupCnt;i++)
		    {
		    	tt.add(matcher.group(i));
		    }
		}
		
		return tt;
	}

}
