package nest.hava.edutills.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.*;

@SuppressWarnings("unchecked")
public class StringUtils {

    public static final String ENCODE_ENGLISH = "ISO-8859-1";
    public static final String ENCODE_KOREAN= "EUC-KR";
    public static final String ENCODE_UTF_8= "UTF-8";
    
    
	public static final char[] KOREAN_FIRST_CHARACTER = { 'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ','ㅎ' };
	public static final char[] KOREAN_SECOND_CHARACTER = { 'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ','ㅡ', 'ㅢ', 'ㅣ' };
	public static final char[] KOREAN_THIRD_CHARACTER = { ' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ','ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ' };

	public static String[] split(String source, String[] separators) {
		String[] rtn = {source};
		
		for(String separator: separators) {			
			int index = source.lastIndexOf(separator);
			if(index > -1) {
				rtn = source.split(separator);
				break;
			}
		}
		return rtn;
	}
	
	public static String[] splitByLastMatchSeparator(String source, String[] separators) {
		String[] rtn = {source};
		
		for(String separator: separators) {			
			int index = source.lastIndexOf(separator);
			if(index > -1) {
				String tmp1 = source.substring(0, index);
				String tmp2 = source.substring(index+1);
				rtn = new String[] {tmp1, tmp2};
				break;
			}
		}
		return rtn;
	}

    /**
     * MD5 변환 알고리즘
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encodeMD5Str(String str) {
        
    	StringBuffer sb = new StringBuffer();
    	
    	try {
	    	MessageDigest di = MessageDigest.getInstance("MD5");
	        di.update(str.getBytes());
	        byte[] md5Code = di.digest();
	        
	        for (int i=0;i<md5Code.length;i++) {
	            String md5Char = String.format("%02x", 0xff&(char)md5Code[i]);
	            sb.append(md5Char);
	        }
    	} catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    		return null;
    	}
        
        return sb.toString();
    }
    
    public static String makeSHA1Hash(String input)
            throws NoSuchAlgorithmException
        {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.reset();
            byte[] buffer = input.getBytes();
            md.update(buffer);
            byte[] digest = md.digest();

            String hexStr = "";
            for (int i = 0; i < digest.length; i++) {
                hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
            }
            return hexStr;
        }
    
    /**
     * URL 프로퍼티 형식의 문자열을 HashMap으로 변환
     *  예) 문자열:key1=4343;key2=489343;name=344343; 을 <key,value> 형태의  HashMap으로 변환
     * @param str
     * @return
     */
    public static HashMap<String, String> parseUrlPropertyStr(String str) {
    	HashMap<String, String> retMap = new HashMap<String, String>();
    	String[] props = str.split(";");
    	
		for (int j=0; j < props.length; j++) {
			String[] paramvalue = props[j].split("=");
			retMap.put(paramvalue[0], paramvalue[1]);
		}
		
    	return retMap;
    }
    
	/**
	 * 문자열의 null 여부를 check하여 null일 경우 ""를 리턴한다.
	 * null이 아닐 경우에는 문자열의 trim()을 호출한 후 리턴한다.
	 * @param comment
	 * @return 공백이나 trim() 결과
	 */
	public static String nullToBlank(Object comment) {
		if (comment == null) return "";
		return String.valueOf(comment).trim();
	}
    
	/**
	 * 문자열의 null 여부를 check하여 null일 경우 ""를 리턴한다.
	 * null이 아닐 경우에는 문자열의 trim()을 호출한 후 리턴한다.
	 * @param comment
	 * @param def
	 * @return 디폴트 값이나 trim() 결과
	 */
	public static String nullToBlank(Object comment, String def) {
		if (comment == null || ("").equals(String.valueOf(comment).trim())) return def;
		return String.valueOf(comment).trim();
	}
	
	public static int getByteLength(String input){           

              int subject_len = input.length();
              int strLength = 0;
              char tempChar[] = new char[subject_len];

              for (int i=0; i<subject_len; i++) {
                      tempChar[i]= input .charAt(i) ;
                      if (tempChar[i] < 128) {
                              strLength++;
                      } else {
                              strLength += 2;
                      }
              }
              return strLength;
     }
	
	
	/**
	 * 입력받은 문자열을 전자로 변환하여 리턴한다.
	 * @param inputString 반자 
	 * @return twoByteString 전자
	 */
	public static String getTwoByteString(String inputString){
		if(inputString==null)inputString="";
		int tmp=0;
		String twoByteString="";
		for(int i=0;i<inputString.length();i++){
			if(inputString.charAt(i) <= 127){
				if((int)inputString.charAt(i)==32){
					tmp = inputString.charAt(i) + 12256;
				}else{
					tmp = inputString.charAt(i) + 65248;
				}
			}else{
				tmp = inputString.charAt(i);
			}
			
			twoByteString = twoByteString + (char)tmp;
		}
		return twoByteString;
	}
	
	/**
	 * 입력받은 문자열을 반자로 변환하여 리턴한다.
	 * @param inputString 전자 
	 * @return oneByteString 반자
	 */
	public static String getOneByteString(String inputString){
		if(inputString==null)inputString="";
		int tmp=0;
		String oneByteString="";
		for(int i=0;i<inputString.length();i++){
			if(inputString.charAt(i)==12288||(inputString.charAt(i) >= 65248 && inputString.charAt(i)<=65375)){
				if((int)inputString.charAt(i)==12288){
					tmp = inputString.charAt(i) - 12256;
				}else{
					tmp = inputString.charAt(i) - 65248;
				}
			}else{
				tmp = inputString.charAt(i);
			}
			
			oneByteString = oneByteString + (char)tmp;
		}
		return oneByteString;
	}
	


	
	/**
	 * Collection, Map, Object[] 등 과 같은 객체들의 값을 
	 * 펼쳐서 String으로 반환한다.
	 * 
	 * @param obj
	 * @param delim
	 * @return 구분자로 연결된 문자열
	 */
	@SuppressWarnings("rawtypes")
	public static String expandArray(Object obj, String delim) {
		StringBuffer buff = new StringBuffer();
		if(obj instanceof Map) {
			obj = ((Map)obj).values(); 
		}
		if(obj instanceof Collection) {
			Iterator iter = ((Collection) obj).iterator();
			while(iter.hasNext()) {
				if(buff.length()>0) buff.append(delim);
				buff.append( String.valueOf(iter.next()) );
			}

		} else if(obj instanceof Object[]) {
			Object[] arr = (Object[]) obj;
            
            int size = arr.length;
            
			for(int i=0; i< size; i++) {
				if(buff.length()>0) buff.append(delim);
				buff.append( String.valueOf(arr[i]) );				
			}
		}
		return buff.toString();
	}
	
	
	/**
	 * 문자열을 특정 delimiter를 이용하여 분리한다.
	 *
	 * @param buf 분리할 문자열
	 * @param delim delimiter
	 * @return 분리된 문자열의 String[]
	 */
    @SuppressWarnings("rawtypes")
	public static String[] explodeToArray(String buf, String delim) {
        if(buf==null) return null;
		StringTokenizer token = new StringTokenizer(buf, delim);
		ArrayList arrList = new ArrayList(30);
		for (; token.hasMoreTokens();) {
			arrList.add(token.nextToken());
		}
		String retval[] = new String[arrList.size()];
        
        int size = retval.length;
        
		for (int i = 0; i < size; i++) {
			retval[i] = (String) arrList.get(i);
		}
		return retval;
	}
	
	/**
	 * 문자열을 특정 delimiter를 이용하여 분리한다.
	 *
	 * @param buf 분리할 문자열
	 * @param delim delimiter

	 * @return 분리된 문자열의 ArrayList
	 */
    @SuppressWarnings("rawtypes")
	public static List explodeToList(String buf, String delim) {
        if(buf==null) return null;
		StringTokenizer token = new StringTokenizer(buf, delim);
		List arrList = new ArrayList(30);
		while (token.hasMoreTokens()) {
			arrList.add(token.nextToken());
		}
		return arrList;
	}
    
    /**
     * key,value의 목록으로 구성된 문자열을 분리하여 Map으로 만든다.<br>
     * 사용예)<br>
     * <code>
     *  // key,value의 구분자를 '='를 사용하고, 각항목은 '|'로 구분하는 문자열
     *  String buf = "key1=value1|key2=value2|key3=value3";
     *  String itemDelim = "|";
     *  String keyDelim = "=";
     * 
     *  // result에는 key1,key2,key3 가 key로 구성되고, 각각의 값이, 
     *  // value1,value2, value3가 되는 Map을 반환한다.
     *  Map result = StringUtil.explodeToMap(buf, itemDelim, keyDelim);
     * </code>
     * 
     * 
     * @param buf 분리할 문자열 
     * @param itemDelim 각 항목들을 분리할 delimiter
     * @param keyDelim key,value를 분리할 delimiter
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static Map explodeToMap(String buf, String itemDelim, String keyDelim){
        if(buf==null) return null;
        StringTokenizer token = new StringTokenizer(buf, itemDelim);
        Map<String,String> map = new HashMap(30);
        while (token.hasMoreTokens()) {
            StringTokenizer mapToken = new StringTokenizer(token.nextToken(), keyDelim);
            String key = null;
            String value = null;
            if(mapToken.hasMoreTokens()) key = mapToken.nextToken();
            if(mapToken.hasMoreTokens()) value = mapToken.nextToken();
            map.put(key, value);
        }
        return map;
    }
	
	
    /**
     * 문자열을 치환한다.
     * @param   src   원본 String
     * @param   oldstr 원본 String 내의 바꾸기 전 문자열
     * @param   newstr 바꾼 후 문자열
     * @return  String 치환이 끝난 문자열 
    */
    public static String replaceStr(String src, String oldstr, String newstr) {
        if (src == null) {
            return null;
        }
        StringBuffer dest = new StringBuffer("");
        int len = oldstr.length();
        int srclen = src.length();
        int pos = 0;
        int oldpos = 0;

        while ((pos = src.indexOf(oldstr, oldpos)) >= 0) {
            dest.append(src.substring(oldpos, pos));
            dest.append(newstr);
            oldpos = pos + len;
        }

        if (oldpos < srclen) {
            dest.append(src.substring(oldpos, srclen));
        }
        return dest.toString();
    }
    
    
    /**
     * htmlString을 브자우저에서 볼수 있는 형대로 변경한다.
     * @param strText
     * @return 브라우져에서 볼수 있는 html 
     */
    public static String toHTML(String strText) {
        String strInput;
        StringBuffer strOutput = new StringBuffer("");
        String convert;
        char strTmp;
        int nCount;

        if (strText == null) {
            strText = "";
        }

        strInput = strText;
        nCount = strInput.length();

        
        for (int i = 0; i < nCount; i++) {

            strTmp = strInput.charAt(i);

            if (strTmp == '<')
                strOutput.append("&lt;");
            else if (strTmp == '>')
                strOutput.append("&gt;");
            else if (strTmp == '&')
                strOutput.append("&amp;");
            else if (strTmp == (char) 37)
                strOutput.append("&#37;");
            else if (strTmp == (char) 34)
                strOutput.append("&quot;");
            else if (strTmp == (char) 39)
                strOutput.append("&#39;");
            else if (strTmp == '#')
                strOutput.append("&#35;");
            else if (strTmp == '\n')
                strOutput.append("<br>");
            else
                strOutput.append(strTmp);

        }

        convert = strOutput.toString();
        return convert;
    }
    
    
    /**
     * 입력된 값을 지정된 글자수만큼 fillChar를 채워서 반환
     * 일련번호 채번시 사용
     * @param data 
     * @param size
     * @return 자리숫를 채운 문자열
     */
    public static String fillString(String data, String fillChar, int size) {
        if (data == null)
            data = "";
        StringBuffer buff = new StringBuffer();
        String str = data.trim();
        int strsize = str.length();
        for (int i = 0; i < size - strsize; i++) {
            buff.append(fillChar);
        }
        buff.append(str);
        return buff.toString();
    }
    
    /**
     * 입력된 값을 지정된 글자수만큼 fillChar를 채워서 반환
     * 
     * @param data 
     * @param padding
     * @param size
     * @return 자리숫를 채운 문자열
     */
    public static String lPad(String data, char padding, int size) {

        if (data == null) {
            data = "";
        }

        int len = data.length();
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<size; i++) {

            if (i == (size-len)) {
                sb.append(data);
                break;
            } else {
                sb.append(padding);                
            }
        }

        return sb.toString();
    }
    
    
    /**
     * 입력된 값을 지정된 글자수만큼 fillChar를 채워서 반환
     * 
     * @param data 
     * @param padding
     * @param size
     * @return 자리숫를 채운 문자열
     */
    public static String rPad(String data, char padding, int size) {

        if (data == null) {
            data = "";
        }

        int len = data.length();
        StringBuffer sb = new StringBuffer(data);
        
        for (int i=0; i<(size - len); i++) {
        	sb.append(padding);
        }

        return sb.toString();
    }


    /**
     * 입력된 Idnfr 에 plus(+) 1을 해준다.
     * 항상 처음 4자리는 식별자이여야 한다.
     * 예) getNextIdnfr("MA0100000002") ==> "MA0100000003"
     * @param idnfr
     * @return 1증가된 문자열
     */
    public static String getNextIdnfr(String idnfr) {
        String result = null;
        if ( idnfr == null || idnfr.length() == 0 ) {
            return result;
        }
        
        int size = idnfr.length();
        if ( idnfr.length() > 4 ) {
            String preStr = idnfr.substring(0,4);
            String postStr = idnfr.substring(4);
            int value = Integer.parseInt(postStr) + 1;
            result = preStr +  fillString(value+"", "0", size);
        }
        return result;
    }
    
    /**
     * 입력된 maxKey에 plus 1을 해준다.
     * 예) getNextSeq("002") ==> "003"
     * @param maxKey
     * @return 1증가된 문자열
     */
    public static String getNextSeq(String maxKey) {
        String result = null;
        if ( maxKey == null || maxKey.length() == 0 ) {
            return result;
        }
        
        int size = maxKey.length();
        int value = Integer.parseInt(maxKey) + 1;
        result = fillString(value + "", "0", size);
        
        return result;
    }
    
    /**
     * double 형태의 데이터를 String 으로 변환해준다.
     * @param d
     * @param inx
     * @return 컴마를 추가한 문자열
     * @throws Exception
     */
    private static String commaMark(double d, int inx) {
            String inxcomma = "";
            for (int i = 0; i < inx; i++) {
                if (i == 0)
                    inxcomma += ".";
                inxcomma += "0";
            }
            java.text.DecimalFormat df = new DecimalFormat(
                    "#,###,###,###,###,###,###,###,##0" + inxcomma);

            return df.format(d);
    }

    /**
     * double 형태의 데이터를 String 으로 변환해준다.
     * 소수점이하 필요없는 0을 제거
     * @param d
     * @param inx
     * @return 컴마를 추가한 문자열
     * @throws Exception
     */
    private static String commaMarkRemoveZero(double d, int inx) {

            String inxcomma = "";
            for (int i = 0; i < inx; i++) {
                if (i == 0)
                    inxcomma += ".";
                inxcomma += "#";
            }
            java.text.DecimalFormat df = new DecimalFormat(
                    "#,###,###,###,###,###,###,###,##0" + inxcomma);

            return df.format(d);

    }
    
    /**
     * double 형태의 데이터를 String 으로 변환해준다.
     * 소수점이하 필요없는 0을 제거
     * @param d
     * @param inx
     * @return 컴마를 추가한 문자열
     * @throws Exception
     */
    private static String removeZero(double d, int inx)  {

            String inxcomma = "";
            for (int i = 0; i < inx; i++) {
                if (i == 0)
                    inxcomma += ".";
                inxcomma += "#";
            }
            java.text.DecimalFormat df = new DecimalFormat(
                    "########################0" + inxcomma);

            return df.format(d);

    }
    
    /**
     * 소숫점없는 double 형태의 데이터를 String 으로 변환해준다.
     * 
     * @param d
     * @return 컴마를 추가한 문자열
     * @throws Exception
     */
    public static String commaMark(Double d)  {
            return commaMark(d.doubleValue(), 0);

    }
    
    /**
     * 용도 : String값의 숫자를 콤마(,)를 포함한 String으로 반환하는 메소드
     * 소수점이하 필요없는 0 제거
     * 00123456.00000 > 123,456
     * 00123456.12000 > 123,456.12
     * @author AA10003
     * @version 
     */
    public static String toAmountFormat(String str){
        String resultStr="";
            String overDecimal="";
            String underDecimal="";
            
            String resOverDecimal="";
            String resUnderDecimal="";
            
            String[] strs = str.split("\\.");
            
            int strsLen = strs.length;
            if(strsLen==2){
                overDecimal = strs[0];
                underDecimal = "0."+strs[1];
                
                resOverDecimal = commaMark(Double.parseDouble(overDecimal));
                resUnderDecimal = commaMarkRemoveZero(Double.parseDouble(underDecimal),5);
            
                String[] resUnderDecimals = resUnderDecimal.split("\\.");
                int resUnderDecimalsLen = resUnderDecimals.length;
                
                if(resUnderDecimalsLen<2){
                    resultStr = resOverDecimal;
                }else{
                    resultStr = resOverDecimal + "." + resUnderDecimals[1];
                }
            }else if(strsLen==1){
                overDecimal = strs[0];
                resOverDecimal = commaMark(Double.parseDouble(overDecimal));
                resultStr = resOverDecimal;
            }else{
                resultStr = str;
            }
        return resultStr;
    }
    
    /**
     * 용도 : String값의 숫자를 소수점이하 필요없는 0 제거
     * 00123456.00000 > 123456
     * 00123456.12000 > 123456.12
     * @author AA10003
     * @version 
     */
    public static String toRemoveZeroFormat(String str){
        
       
        String resultStr="";
        String overDecimal="";
        String underDecimal="";
        
        String resOverDecimal="";
        String resUnderDecimal="";
        
        String[] strs = str.split("\\.");
        
        int strsLen = strs.length;
        if(str == null || ("").equals(str.trim())){
            resultStr = str;
        }else if(strsLen==2){
            overDecimal = strs[0];
            underDecimal = "0."+strs[1];
            
            resOverDecimal = removeZero(Double.parseDouble(overDecimal),0);
            resUnderDecimal = removeZero(Double.parseDouble(underDecimal),5);
        
            String[] resUnderDecimals = resUnderDecimal.split("\\.");
            int resUnderDecimalsLen = resUnderDecimals.length;
            
            if(resUnderDecimalsLen<2){
                resultStr = resOverDecimal;
            }else{
                resultStr = resOverDecimal + "." + resUnderDecimals[1];
            }
        }else if(strsLen==1){
            overDecimal = strs[0];
            resOverDecimal = removeZero(Double.parseDouble(overDecimal),0);
            resultStr = resOverDecimal;
        }else{
            resultStr = str;
        }
        return resultStr;
    }
    
    /**
     * 용도 : String값의 숫자의 앞뒤 0 제거 
     * @author AA10003
     * @version 
     */
    public static String removeZero(String str,int type){
        
       
        String resultStr="";
        String overDecimal="";
        String underDecimal="";
        
        String resOverDecimal="";
        String resUnderDecimal="";
        
        String[] strs = str.split("\\.");
        
        int strsLen = strs.length;
        if(str == null || ("").equals(str.trim())){
            resultStr = str;
        }else if(strsLen==2){
            overDecimal = strs[0];
            underDecimal = "0."+strs[1];
            
            resOverDecimal = removeZero(Double.parseDouble(overDecimal),0);
            if(type>0){
                resUnderDecimal = underDecimal;
            }else{
                resUnderDecimal = removeZero(Double.parseDouble(underDecimal),0);
            }
            String[] resUnderDecimals = resUnderDecimal.split("\\.");
            int resUnderDecimalsLen = resUnderDecimals.length;
            
            if(resUnderDecimalsLen<2){
                resultStr = resOverDecimal;
            }else{
                resultStr = resOverDecimal + "." + resUnderDecimals[1];
            }
        }else if(strsLen==1){
            overDecimal = strs[0];
            resOverDecimal = removeZero(Double.parseDouble(overDecimal),0);
            resultStr = resOverDecimal;
        }else{
            resultStr = str;
        }
        return resultStr;
    }


    /**
     * 파일명에서 확장자를 추출
     * @param 	filename
     * @return	String 확장자
     */
    public static String getExtName(String filename) {
    	String ext = "";
    	
    	if (filename != null) {
    		int idx = filename.lastIndexOf('.');
    		if (idx > 0) {
    			ext = filename.substring(idx+1);
    		}
    	}
    	
    	return ext;
    }
    
    /**
     * String 문자열을 주어진 delimiter로 split 후 List로 리턴
     * @param  param
     * @param  delimiter
     * @return List<String>
     */
    public static List<String> getStringList(String param, String delimiter) throws NullPointerException{
		if(param == null){
			throw new NullPointerException("parameter is null");
		}
		String[] paramArray = param.split(delimiter);
		return Arrays.asList(paramArray);
	} 
    
    /**
	 * return an empty string
	 * @param str
	 * @param defaultValue
	 * @return
	 */
	 public static String defaultIfEmpty(String str , String defaultValue)
    {
    	String retValue = "";
    	
    	if(str==null) retValue = defaultValue;
    	else if(str.equals(""))retValue = defaultValue;
    	else retValue = str;
    	
    	return retValue;
    }
    
    
	 /**
	  * return an empty string
	  * @param obj
	  * @param defaultValue
	  * @return
	  */
    public static String defaultIfEmpty(Object obj , String defaultValue)
    {
    	String retValue = "";
    	
    	if(obj==null) retValue = defaultValue;
    	else if(obj instanceof String){
    		if(((String)obj).equals(""))retValue = defaultValue;
    		else {
        		retValue = (String)obj;
        	}
    	}else {
    		 retValue = String.valueOf(obj);
    	}
    	
    	
    	
    	return retValue;
    }
    
	public static boolean isNullOrEmpty(String s) {
		if (s == null) {
			return true;
		}
		return s.isEmpty();
	}
	
	
	public static boolean isNotNullOrEmpty(String s) {
		if (s == null) {
			return false;
		}
		if (s.equals("")){
			return false;
		}
		return !s.isEmpty();
	}
    
    /**
     * return a text trimmed
     * @param str
     * @return
     */
    public static String trim(String str)
    {
    	if(str!=null) return str.trim();
    	return str;
    }
    
    
    public static String subString(String source, int startPos, int endPos)
    {
    	return org.apache.commons.lang3.StringUtils.substring(source, startPos, endPos);
    	
    }
    
	public static String trim(String string, int byteLength) {
		for (int index = 0, len = string.length(), bytes = 0; index < len; ++index) {
			bytes += String.valueOf(string.charAt(index)).getBytes().length;

			if (bytes > byteLength)
				return string.substring(0, index);
		}

		return string;
	}
	public static long toHash(String str)
	{
		
		str = defaultIfEmpty(str, "");
		
		long hash = 0;
		
		for (int i = 0; i < str.length(); i++) {
			hash = 257 * hash + str.charAt(i);
		}
		return hash;
	}
	
	
	public static String removeNonKoreanChars(String str)
	{
		StringBuffer sb = new StringBuffer();
		char[] charArray=str.toCharArray();
		for (int j=0; j<charArray.length; j++) {
			if (charArray[j]>='\uAC00' && charArray[j]<='\uD7A3'){
				sb.append(charArray[j]);
			}
		}
		
		return sb.toString();
	}

	public static String removeSpecialCharsForNAS(String str)
	{
		str= str.replaceAll("\\(주\\)", "").trim();
		str= str.replaceAll("\\(주식회사\\)", "").trim();
		str = remove(str);
		if(!str.contains("이디야커피"))
		{
			str= str.replaceAll("이디야", "이디야커피").trim();
		}
		str= str.replaceAll("파리바게트", "파리바게뜨").trim();
		
		
		return str;
	}
	
	/**
	 * check whether difference of middle chars can be accepted 
	 * @param target
	 * @param feature
	 * @return
	 */
	public static int compareTwoStrLessthan3Chars(String feature , String target)
	{
		
		String strPre1 = subString(target, 0, 2);
		String strPre2 = subString(feature, 0, 2);
		
		String strPost1 = subString(target, target.length()-2, target.length());
		String strPost2 = subString(feature, feature.length()-2, feature.length());
		
		if(strPre1.equalsIgnoreCase(strPre2) && strPost1.equalsIgnoreCase(strPost2)){
			char [] strArray1 = target.toCharArray();
			char [] strArray2 = feature.toCharArray();
			
			for(char c : strArray1)
			{
				boolean match = false;
				for(char b:strArray2)
				{
					if(c == b)
					{
						match = true;
					}
				}
				
				if(!match) return -1;
			}
			
			return 1;
			
		}else if(!strPost1.equalsIgnoreCase(strPost2)) {
			
			if(!feature.contains(target))
			{
				return 0;
			}
			
			return 1;
			
		}else {
			return 1;
		}
		
	}
	
	
	public static boolean compareTwoStr(String str1, String str2)
	{
		char[] lResult = null;
		
		Map<String,Integer> str1Map = new HashMap<>();
		for (int i = 0; i < str1.length(); i++) {
			lResult = StringUtils.koreaJasoDivide(str1.charAt(i));
			
			Integer cnt;
			for (int j = 0; j < lResult.length; j++)
			{
				String m = String.valueOf(lResult[j]);
				m = m.trim();
				if(!StringUtils.isNullOrEmpty(m))
				{
					cnt = str1Map.get(m);
					if(cnt == null)
					{
						cnt = 1;
					}else {
						cnt = cnt + 1;
					}
					
					str1Map.put(m, cnt);
				}
				
			}
		}
		
		Map<String,Integer> str2Map = new HashMap<>();
		for (int i = 0; i < str2.length(); i++) {
			lResult = StringUtils.koreaJasoDivide(str2.charAt(i));
			
			Integer cnt;
			for (int j = 0; j < lResult.length; j++)
			{
				String m = String.valueOf(lResult[j]);
				m = m.trim();
				if(!StringUtils.isNullOrEmpty(m))
				{
					cnt = str2Map.get(m);
					if(cnt == null)
					{
						cnt = 1;
						
					}else {
						cnt = cnt + 1;
					}
					
					str2Map.put(m, cnt);
				}
				
			}
		}
		
		
		Set<String> key1set = str1Map.keySet();
		Set<String> key2set = str2Map.keySet();
		
		int key1size = key1set.size();
		int key2size = key2set.size();
		Integer diff;
		if(key1size >= key2size)
		{
			diff = differcnt(str1Map,str2Map);
			
		}else {
			diff = differcnt(str2Map,str1Map);
		}
		
		if(diff > 3)
		{
			return false;
		}
		
		return true;
		
	}
	
	public static String replaceAll(String [] sizeIndiators, String str)
	{
		for(String s : sizeIndiators)
		{
			str = str.toLowerCase().replace(s.toLowerCase(), "");
		}
		
		return str;
	}
	
	
	private static int differcnt(Map<String,Integer> str1Map,Map<String,Integer> str2Map)
	{
		
		Iterator<String> itr1 = str1Map.keySet().iterator();
		String key;
		Integer cnt1;
		Integer cnt2;
		Integer totalDiff=0;
		Integer diff=0;
		
		while(itr1.hasNext())
		{
			key = itr1.next();
			cnt1 = str1Map.get(key);
			cnt2 = str2Map.remove(key);
			
			cnt1 = MoreObjects.firstNonNull(cnt1, 0);
			cnt2 = MoreObjects.firstNonNull(cnt2, 0);
			
			diff = Math.abs(cnt1-cnt2);
			totalDiff= totalDiff +diff;
			
		}
		
		Iterator<String> itr2 = str2Map.keySet().iterator();
		
		while(itr2.hasNext())
		{
			key = itr2.next();
			cnt2 = str2Map.get(key);
			cnt2 = MoreObjects.firstNonNull(cnt2, 0);
			diff = cnt2;
			totalDiff= totalDiff +diff;
			
		}
		
		
		return totalDiff;
	}
	
	
	public static char[] koreaJasoDivide(char pInputChar) {
		char[] lResult = new char[3];
		pInputChar -= 0xAC00;
		
		if (pInputChar >= 0 && pInputChar <= 11172)
		{
			int lFirstIdx = (int) ((pInputChar / (char) 21 / (char) 28));
			int lSecondIdx = (int) ((pInputChar % ((char) 21 * (char) 28)) / (char) 28);
			int lThirdIdx = (int) ((pInputChar % (char) 28));
			lResult[0] = KOREAN_FIRST_CHARACTER[lFirstIdx];
			lResult[1] = KOREAN_SECOND_CHARACTER[lSecondIdx];
			lResult[2] = KOREAN_THIRD_CHARACTER[lThirdIdx];
			return lResult;
		}else {
			lResult = new char[1];
			lResult[0] = (char)(pInputChar +0xAC00);
		}
		
		return lResult;
	}
	
	public static boolean match(String [] tt, String str)
	{
		for(String t: tt)
		{
			if(t.equalsIgnoreCase(str))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean contain(String [] tt, String str)
	{
		str = str.toLowerCase();
		for(String t: tt)
		{
			if(str.contains(t.toLowerCase()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static String remove(String source)
	{
		if(!StringUtils.isNullOrEmpty(source)){
			source = source.replaceAll(" ", "");
			source = source.replaceAll("\\)", "");
			source = source.replaceAll("\\(", "");
			source = source.replaceAll("）", "");
			source = source.replaceAll("（", "");
			source = source.replaceAll("\"", "");
			source = source.replaceAll("'", "");
			source = source.replaceAll("\\+", "");
			source = source.replaceAll("#", "");
			source = source.replaceAll("\\!", "");
			source = source.replaceAll(",", "");
			source = source.replaceAll("&", "");
			source = source.replaceAll(";", "");
			source = source.replaceAll(":", "");
			source = source.replaceAll("-", "");
			source = source.replaceAll("\\.", "");
			source = source.replaceAll("\\[", "");
			source = source.replaceAll("㈜", "");
			source = source.replaceAll("/", "");
		}
		
		return source;
	}
	
	public static String removeType1(String source)
	{
		if(!StringUtils.isNullOrEmpty(source)){
			source = source.replaceAll("）", "");
			source = source.replaceAll("（", "");
			source = source.replaceAll("\"", "");
			source = source.replaceAll("'", "");
			source = source.replaceAll("\\+", "");
			source = source.replaceAll("#", "");
			source = source.replaceAll("\\!", "");
			source = source.replaceAll(",", "");
			source = source.replaceAll("&", "");
			source = source.replaceAll(";", "");
			source = source.replaceAll(":", "");
			source = source.replaceAll("-", "");
			source = source.replaceAll("\\.", "");
			source = source.replaceAll("㈜", "");
			source = source.replaceAll("/", "");
		}
		
		return source;
	}
	
	public static String removeType2(String source)
	{
		if(!StringUtils.isNullOrEmpty(source)){
			source = source.replaceAll("）", "");
			source = source.replaceAll("（", "");
			source = source.replaceAll("\"", "");
			source = source.replaceAll("'", "");
			source = source.replaceAll("\\+", "");
			source = source.replaceAll("#", "");
			source = source.replaceAll("\\!", "");
			source = source.replaceAll(",", "");
			source = source.replaceAll("&", "");
			source = source.replaceAll(";", "");
			source = source.replaceAll(":", "");
			source = source.replaceAll("-", "");
			source = source.replaceAll("㈜", "");
			source = source.replaceAll("/", "");
		}
		
		return source;
	}

	public static String removeType3(String source)
	{
		if(!StringUtils.isNullOrEmpty(source)) {
			source = source.replaceAll("\\[.+?\\]|\\(.+?\\)|［(.+?)］", "");
			source = removeSpecialChars(source);
		}
		return source;
	}
	
	public static String removeType4(String source)
	{
		if(!StringUtils.isNullOrEmpty(source)){
			source = source.replaceAll(" ", "");
			source = source.replaceAll("\\)", "");
			source = source.replaceAll("\\(", "");
			source = source.replaceAll("）", "");
			source = source.replaceAll("（", "");
			source = source.replaceAll("\"", "");
			source = source.replaceAll("'", "");
			source = source.replaceAll("\\+", "");
			source = source.replaceAll("#", "");
			source = source.replaceAll("\\!", "");
			source = source.replaceAll(",", "");
			source = source.replaceAll("&", "");
			source = source.replaceAll(";", "");
			source = source.replaceAll(":", "");
			source = source.replaceAll("-", "");
			source = source.replaceAll("\\.", "");
			source = source.replaceAll("\\[", "");
			source = source.replaceAll("㈜", "");
			source = source.replaceAll("/", "");
			source = source.replaceAll("\\{", "");
			source = source.replaceAll("\\}", "");
			source = source.replaceAll("[*]", "");
			source = source.replaceAll("_", "");
		}
		
		return source;
	}
	
	public static String removeSpace(String source)
	{
		if(!StringUtils.isNullOrEmpty(source)){
			source = source.replaceAll(" ", "");
		}
		
		return source;
	}
	

	public static String removeSpecialChars(String source)
	{
		if(!StringUtils.isNullOrEmpty(source)){
			source = source.replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\u4200-\u9FA5\uF900-\uFA2D]", "");
		}
		
		return source;
	}
	
	public static String removeSpecialCharsForGoodsNumExtract(String source)
	{
		if(!StringUtils.isNullOrEmpty(source)){
			source = source.replaceAll(" ", "");
			source = source.replaceAll("\\.", "");
			source = source.replaceAll("_$", "");
			source = source.replaceAll("-$", "");
		}
		
		return source;
	}
	
	public static String removeSpecialCharsForGoodsNumDisplay(String source)
	{
		if(!StringUtils.isNullOrEmpty(source)){
			source = source.replaceAll(" ", "");
			source = source.replaceAll("\\.", "");
			source = source.replaceAll("_", "");
			source = source.replaceAll("-", "");
		}
		
		return source;
	}
	
	public static String removeForPrice(String source)
	{
		if(!StringUtils.isNullOrEmpty(source)){
			source = source.replaceAll(",", "");
			source = source.replaceAll("~", "");
		}
		
		return source;
	}
	
	
	public static String toHalfChar(String src) {
		StringBuffer strBuf = new StringBuffer();
		char c = 0;
		int nSrcLength = src.length();
		for (int i = 0; i < nSrcLength; i++) {
			c = src.charAt(i);
			if (c >= '！' && c <= '～') {
				c -= 0xfee0;
			} else if (c == '　') {
				c = 0x20;
			}
			strBuf.append(c);
		}
		return strBuf.toString();
	}
	
	public static boolean isNumeric(String i)
	{
		return org.apache.commons.lang3.StringUtils.isNumeric(i);
	}
	
	public static int nullToNum(Object obj, int defValue) {
		if(obj == null) {
			return defValue;
		}
		return nullToNum(obj.toString(), defValue);
	}
	
	public static int nullToNum(String str, int defValue) {
		if(str == null || str.trim().length() == 0) {
			return defValue;
		}
		// 기존에 사용하던 isNumeric은 음수인 경우 오류가 발생함..
//		if(!isNumeric(str)) {
//			return defValue;
//		}
		return (int) Double.parseDouble(makeOnlyNumber(str));
	}
	
	public static int nullToZero(Object obj) {
		return nullToNum(obj, 0);
	}
	
	public static int nullToZero(String str) {
		return nullToNum(str, 0);
	}
	
	public static int nullToOne(Object obj) {
		return nullToNum(obj, 1);
	}
	
	public static int nullToOne(String str) {
		return nullToNum(str, 1);
	}
	
	public static String getString(Object obj) {
		if(obj == null) {
			return null;
		}
		return obj.toString();
	}
	
	public static String makeOnlyNumber(String str) {
		return makeOnlyNumber(str, false);
	}
	
	public static String makeOnlyNumber(String str, boolean isIntValue) {
		if(str == null || str.length() == 0) {
			return "0";
		}
		StringBuilder sb = new StringBuilder();
		int len = str.length();
		int count = 0;
		int dotCount = 0;
		for(int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if(i == 0) { // 첫자리에 한해서 부호가 올 수도 있다.
				if(c == '+' || c == '-') {
					sb.append(c);
				}
			}
			if((c >= '0' && c <= '9') || (i != 0 && dotCount == 0 && c == '.')) {
				count++;
//				if(isIntValue && c == '.') { // int형이라면 .이후.. 는 불가..
//					break;
//				}
				sb.append(c);
			}
		}
		if(count == 0) { // 부호만 있고 숫자가 없는 경우가 있을 수도 있다.
			return "0";
		}
		return sb.toString();
	}

	public static String calculateRate(String price, String discountPrice) {
		int orgPrice = Integer.parseInt(makeOnlyNumber(price));
		int salePrice = Integer.parseInt(makeOnlyNumber(discountPrice));
		return "" + (((float) orgPrice - (float) salePrice) / (float) orgPrice * 100);
	}
	public static String calculateRate2(String price, String discountPrice) {
		int orgPrice = Integer.parseInt(makeOnlyNumber(price));
		int salePrice = Integer.parseInt(makeOnlyNumber(discountPrice));
		return "" +Math.round((((float) orgPrice - (float) salePrice) / (float) orgPrice * 100));
	}
	
	public static String join(List<String> list, String str) {
		return org.apache.commons.lang3.StringUtils.join(list, str);
	}

	
	public static String join(Set<String> list, String str) {
		return org.apache.commons.lang3.StringUtils.join(list, str);
	}
	
	/**
	 * 
	 * <pre>
	 *  Description : CR and LF를 제거한다.
	 * </pre>
	 * @Method Name : removeCRLF
	 * @date : 2018. 2. 10.
	 * @author : BaeMunHwan
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	Date				Writer						Content  
	 *	----------- ------------------- ---------------------------------------
	 *	2018. 2. 10.		BaeMunHwan				Initial Write
	 *	-----------------------------------------------------------------------
	 * 
	 * @param tt
	 * @return
	 */
	public static String removeCRLF(String tt)
	{
		if(tt !=null)
		{
			tt = tt.replaceAll("(\r\n|\r|\n|\n\r)", " ");
		}
		
		
		return tt;
	}
	
	public static String removeCRLFTAB(String tt)
	{
		if(tt !=null)
		{
			tt = tt.replaceAll("(\r|\n|\t)", "");
		}
		
		
		return tt;
	}
	
	public static String removeKoreanChars(String str)
	{
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotNullOrEmpty(str))
		{
			for(int i=0; i<str.length();i++)
			{
				String tt = str.substring(i, i+1);
				tt = tt.replaceAll("[가-힣]+", "");
				
				if(StringUtils.isNotNullOrEmpty(tt))
				{
					sb.append(tt.trim());
				}
				
			}
		}
		
		
		return sb.toString();
	}
	
	
	
}
