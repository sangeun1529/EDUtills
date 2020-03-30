package nest.hava.edutills.compress;


import nest.hava.edutills.base64.Base64Utils;
import nest.hava.edutills.match.MatchUtils;
import nest.hava.edutills.util.StringUtils;

public class WspiderCompressUtils {
	
	
	public static String compress(String data, int byteLength)
	{
		data = StringUtils.defaultIfEmpty(data, "");
		if(data.getBytes().length > byteLength)
		{
			try{
				
				StringBuffer sb = new StringBuffer();
				sb.append("compress->");
				sb.append(Base64Utils.encodeString(ZipCompressor.compress(data.getBytes())));
				
				return sb.toString();
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	
	public static String decompress(String data)
	{
		data = StringUtils.defaultIfEmpty(data, "");
		if(MatchUtils.fullMatch("^compress->.*", data))
		{
			String encodedData = MatchUtils.partialMatchAndFirstRemove("^compress->", data);
			try{
			
				return ZipCompressor.decompressToString(Base64Utils.decode(encodedData));
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return data;
	}

}
