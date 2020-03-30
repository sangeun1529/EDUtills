package nest.hava.edutills.util;

public class ColorUtils {
	
	private static String [] delimeters = {",","/"};
	
	
	/**
	 * {보라색,자주색}
	 * @param str 보라색,자주색
	 * @return
	 */
	public static String[] split(String str)
	{
		String [] tt = null;
		for(String s : delimeters)
		{
			if(str.contains(s))
			{
				tt = str.split(s);
			}
		}
		
		if(tt == null)
		{
			return new String[]{str};
		}
		
		return tt;
	}

}
