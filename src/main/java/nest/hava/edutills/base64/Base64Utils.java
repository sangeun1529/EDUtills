package nest.hava.edutills.base64;

import java.util.Base64;

public class Base64Utils {
	
	
	public static String encodeString(String data)
	{
		return encodeString(data.getBytes());
	}
	
	public static String encodeString(byte [] raw)
	{
		byte [] encoded =  Base64.getEncoder().encode(raw);
		
		return new String(encoded);
	}
	
	
	public static String decodeString(String encodedData)
	{
		return decodeString(encodedData.getBytes());
	}
	
	public static byte[] decode(String encodedData)
	{
		return decode(encodedData.getBytes());
	}
	
	public static String decodeString(byte [] encode)
	{
		byte [] decoded =  Base64.getDecoder().decode(encode);
		
		return new String(decoded);
	}
	
	public static byte[] decode(byte [] encode)
	{
		byte [] decoded =  Base64.getDecoder().decode(encode);
		
		return decoded;
	}

}
