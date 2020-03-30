package nest.hava.edutills.util;

public class PhoneNumberUtils {
	
	
	
	public static String getRightPhoneNumber(String phoneNumber)
	{
		String [] tt = phoneNumber.split("-");
		
		String pNumber;
		if(tt.length ==3 && tt[0].length()==4 )
		{
			//remove the first index number which is tt[0];
			StringBuffer sb = new StringBuffer();
			sb.append(tt[1]);
			sb.append(tt[2]);
			
			pNumber = sb.toString();
		}
		else {
			pNumber = phoneNumber.replaceAll("-", "");
			pNumber = pNumber.replaceAll(" ", "");
		}
		
		
		return pNumber;
	}

}
