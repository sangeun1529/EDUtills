package nest.hava.edutills.tokenizer;

import nest.hava.edutills.match.MatchUtils;
import nest.hava.edutills.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SpaceTokenizer {


	private static String regExp = "[\\s+,]";
	
	public static boolean containDelimeter(String str)
	{
		return MatchUtils.partialMatch(regExp, str);
	}

	public static List<String> tokenize(String str)
	{
		String [] tokens = str.split(regExp);
		
		List<String> tokenList = Arrays.asList(tokens);
		
		return tokenList;
		
	}
	
	public static List<String> controllableTokenize(String str)
	{
		String [] tokens = str.split(regExp);
		
		List<String> tokenList = new ArrayList<String>(Arrays.asList(tokens));
		
		return tokenList;
		
	}
	
	
	public static String deTokenize(List<String> ttList)
	{
		StringBuilder sb = new StringBuilder();
		
		for(String t: ttList)
		{
			sb.append(t).append(" ");
		}
		
		return sb.toString().trim();
	}
	
	
	public static void tokenize(String str,HashSet<String> dataList)
	{
		
		String [] tokens = str.split(regExp);
		
		for(String token:tokens)
		{
			if(!StringUtils.isNullOrEmpty(token)){
				dataList.add(token.toLowerCase());
			}
			
		}
		
	}
}
