package nest.hava.edutills.util;

import java.math.BigDecimal;

public class PriceUtils {
	
	
	public static Integer getPriceWithCharacters(String strPrice)
	{
		strPrice = StringUtils.removeKoreanChars(strPrice);
		strPrice = StringUtils.removeForPrice(strPrice);
		strPrice = StringUtils.removeType2(strPrice);
		
		Integer price = Integer.parseInt(strPrice);
		
		return price;
	}
	
	
	public static Long getPrice(String price)
	{
		
		if(StringUtils.isNullOrEmpty(price))
		{
			return 0L;
		}else {
			price =  StringUtils.remove(price);
			
			try{
				Long lPrice = Long.parseLong(price);
				return lPrice;
			}catch(Exception e)
			{
				return 0L;
			}
			
		}
		
	}

	public static BigDecimal getPriceAsBigDecimal(String price)
	{
		
		if(StringUtils.isNullOrEmpty(price))
		{
			return new BigDecimal("0");
		}else {
			price =  StringUtils.remove(price);
			
			try{
				BigDecimal bPrice = new BigDecimal(price);
				return bPrice;
			}catch(Exception e)
			{
				return new BigDecimal("0");
			}
			
		}
		
	}
	
	
	public static long getDiscountRate(String discountRate)
	{
		discountRate = StringUtils.removeType2(discountRate);
		
		try{
			
			Double d = Double.parseDouble(discountRate);
			return (long)Math.floor(d);
			
		}catch(Exception e)
		{
			return 0L;
		}
		
	}

}
