package nest.hava.edutills.util;

public class FivePointUtils {
	
	
	
	public static double convert (double total, double point)
	{
		double totalPoint = 5D;
		double fivePoint ;
		
		fivePoint = (totalPoint * point) / total;
		
		fivePoint = Math.round((fivePoint * 100));
		fivePoint = fivePoint/100;
		
		return fivePoint;
	}
	

}
