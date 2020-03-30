package nest.hava.edutills.util;

import java.io.File;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class CommonUtils {
	
	public static String nvl(Object obj, String repStr){
		String str ="";
		try{
			str = (String)obj;
			if(str.equals("") || str.equals(null) || str.equals("null") || str == null){
				str = repStr;
			}
		}catch(NullPointerException npe){
			str = repStr;
		}
		
		return str;
	}
	
	public static String dateReplace(String date){
		String str= "";
		
			if(date.equals("") || date.equals(null) || date.equals("null") || date == null){
				str=date;
			} else {
				str=date.replace("-","");
				str=str.replace("T", "");
				str=str.replace(":", "");
				str=str+"000";
			}
		return str;
		
	}
	
    
    //-----------------------------------------------------------------------

    /**
     * <p>Checks if a String is empty ("") or null.</p>
     *
     * <pre>
     * CommonUtils.isEmpty(null)      = true
     * CommonUtils.isEmpty("")        = true
     * CommonUtils.isEmpty(" ")       = true
     * CommonUtils.isEmpty("bob")     = false
     * CommonUtils.isEmpty("  bob  ") = false
     * </pre>
     *
     * <p>NOTE: This method changed in Lang version 2.0.
     * It no longer trims the String.
     * That functionality is available in isBlank().</p>
     *
     * @param str  the String to check, may be null
     * @return <code>true</code> if the String is empty or null
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 1. 기능 : null이나 empty string인 경우 공백을 리턴, 그 이외에는 원래 값을 리턴
     * 2. 처리 개요 :
     *  - null이나 empty string인 경우 공백을 리턴, 그 이외에는 원래 값을 리턴
     * 3. 주의사항
     *
     * @param attribute 
     * @return String
     * @exception 
     */
    public static String nullCheckSetSpace(String attribute) {
        if (CommonUtils.isEmpty(attribute)) {
            return " ";
        }
        return attribute;
    }

    /**
     * 1. 기능 : null이나 empty string인 경우 empty string을 리턴, 그 이외에는 원래 값을 리턴
     * 2. 처리 개요 :
     *  - null이나 empty string인 경우 empty string을 리턴, 그 이외에는 원래 값을 리턴
     * 3. 주의사항
     *
     * @param attribute 
     * @return String
     * @exception 
     */
    public static String nullCheckSetEmpty(String attribute) {
        if (CommonUtils.isEmpty(attribute)) {
            return "";
        }
        return attribute;
    }

    /**
     * 1. 기능 : null이나 empty string인 경우 String 0을 리턴, 그 이외에는 원래 값을 리턴
     * 2. 처리 개요 :
     *  - null이나 empty string인 경우 String 0을 리턴, 그 이외에는 원래 값을 리턴
     * 3. 주의사항
     *
     * @param attribute
     * @return String
     * @exception 
     */
    public static String nullCheckSetZero(String attribute) {
        if (CommonUtils.isEmpty(attribute)) {
            return "0";
        }
        return attribute;
    }

    /**
     * 1. 기능 : null이나 empty string인 경우 String 0을 리턴, 그 이외에는 원래 값을 리턴
     * 2. 처리 개요 :
     *  - null이나 empty string인 경우 String 0을 리턴, 그 이외에는 원래 값을 리턴
     * 3. 주의사항 : 입력받는값은 반드시 숫자값이어야 한다.
     *
     * @param attribute
     * @return String
     * @exception 
     */
    public static int nullCheckSetIntZero(String attribute) {
        if (CommonUtils.isEmpty(attribute)) {
            return 0;
        }
        return Integer.parseInt(attribute);
    }

    /**
     * 1. 기능 : null이나 empty string인 경우 double 0을 리턴, 그 이외에는 원래 값을 리턴
     * 2. 처리 개요 :
     *  - null이나 empty string인 경우 double 0을 리턴, 그 이외에는 원래 값을 리턴
     * 3. 주의사항 : 입력받는값은 반드시 숫자값이어야 한다.
     *
     * @param attribute
     * @return String
     * @exception 
     */
    public static double nullCheckSetDouble(String attribute) {
        if (CommonUtils.isEmpty(attribute)) {
            return Double.parseDouble("0");
        }
        return Double.parseDouble(attribute);
    }

    /**
     * 1. 기능 : null이나 empty string인 경우 BigDecimal("0")을 리턴, 그 이외에는 원래 값을 리턴  
     * 2. 처리 개요 :
     *  - null이나 empty string인 경우 BigDecimal("0")을 리턴, 그 이외에는 원래 값을 리턴  
     * 3. 주의사항
     *
     * @param attribute 숫자값 string
     * @return BigDecimal
     * @exception 
     */
    public static BigDecimal nullCheckSetBigDecimal(String attribute) {
        if (CommonUtils.isEmpty(attribute)) {
            return new BigDecimal("0");
        }
        return new BigDecimal(attribute);
    }

    /**
     * 1. 기능 : null이나 empty string인 경우 BigDecimal("0")을 리턴, 그 이외에는 원래 값을 리턴
     * 2. 처리 개요 :
     *  - null이나 empty string인 경우 BigDecimal("0")을 리턴, 그 이외에는 원래 값을 리턴
     * 3. 주의사항
     *
     * @param attribute 숫자값
     * @return BigDecimal
     * @exception 
     */
    public static BigDecimal nullCheckSetBigDecimal(BigDecimal attribute) {
        if (attribute == null) {
            return new BigDecimal("0");
        }
        return attribute;
    }

    /**
     * 1. 기능 : null이나 empty string인 경우 0을 리턴, 그 이외에는 원래 값의 길이를 리턴
     * 2. 처리 개요 :
     *  - null이나 empty string인 경우 0을 리턴, 그 이외에는 원래 값의 길이를 리턴
     * 3. 주의사항
     *
     * @param attribute 
     * @return BigDecimal
     * @exception
     */
    public static int getStringLength(String attribute) {

        int iLen = 0;
        if (CommonUtils.isEmpty(attribute)) {
            iLen = 0;
        } else {
            iLen = attribute.trim().length();
        }
        return iLen;
    }


    /**
     * 소수점의 0값을 제거한다. ex) 0.12300 -> 0.123
     * @param value
     * @return
     */
    public static String zeroPointValueRemove(Object value) {

        if (value == null) {
            return "";
        }

        StringBuffer formattedValue = new StringBuffer();

        String changeValue = null;
        if (value instanceof String) {
            changeValue = (String)value;
        }
        else if (value instanceof BigDecimal) {
            changeValue = ((BigDecimal)value).toString();
        }
        else {
            return "";
        }

        // 소수점의 위치를 찾는다.
        int point = changeValue.toString().indexOf(".");
        // 소수점이 포함되었다면..
        if (point > -1) {
            String intValue   = changeValue.toString().substring(0, point);
            String pointValue = changeValue.toString().substring(point + 1);

            char[] charArray = pointValue.toCharArray();
            int len = charArray.length;
            int pos = charArray.length;
            for (int idx=len-1; idx>=0; idx--) {
                if (charArray[idx] > 0x30) {
                    break;
                }
                pos--;
            }

            if (pos > 0) {
                formattedValue.append(intValue).append(".").append(pointValue.substring(0, pos));
            } else {
                formattedValue.append(intValue);
            }
        } else {
            formattedValue.append(changeValue);
        }
        
        return formattedValue.toString();
    }

    /**
     * 랜덤 ID를 생성한다.
     * @param prefix 테이블 뒤 4자리 문자, 예) TSPDMLD21 > LD21
     * @param length ID의 prefix를 제외한 자리수 
     * @return
     */
    public synchronized static String generateRandomId(String prefix, int length) {

        String zero = "";
        for (int i=0; i<length; i++) {
            zero = zero + "0";
        }

        double plus = Double.parseDouble("1" + zero);

        DecimalFormat messageIdFormat = new DecimalFormat(zero);

        Random random = new Random(System.currentTimeMillis());
        double plusDouble = random.nextDouble() * plus;
        return prefix + messageIdFormat.format(plusDouble);
    }

    /**
     * EBCDIC코드를 ASCII코드로 변환한다. 완벽한 코드변환은 안되고 또한 변환건이 발생할때마다 추가해야 한다.
     * 
     * @param ebcdic
     * @return
     */
    public static String ebcdicToAscii(String ebcdic) {

        if (ebcdic == null || "".equals(ebcdic)) return "";

        String ascii = null;

        char[] charArray = ebcdic.toCharArray();

        for (int i=0, loopCnt=charArray.length; i<loopCnt; i++) {

            if (charArray[i] >= 0xFF01 && charArray[i] <= 0xFF5E) {
                // =============================================================
                // 전자와 반자의 차이값. 전자에서 0xFEE0을 빼면 반자가 되고,
                // 반대로 반자에서 0xFEE0을 더하면 전자가 된다.
                // 단, 스페이스(공백)은 별도로 처리해야한다. (아래 별도처리)
                // =============================================================
                charArray[i] -= 0xFEE0;
            }
            else if (charArray[i] == 0x3000) {
                // =============================================================
                // 전자 스페이스(0x3000)는 반자 스페이스(0x20)으로 변경한다.
                // =============================================================
                charArray[i] = 0x20;
            }
            else if (charArray[i] == 0xB7) {
                // =============================================================
                // 전자 가운데점(0xA1A4)는 반자 콤마(0x2C)로 변경한다.
                // =============================================================
                charArray[i] = 0x2C;
            }
            else if (charArray[i] == 0xfffd) {
                // =============================================================
                // 깨진글자(0xfffd)는 반자 스페이스(0x20)로 변경한다.
                // =============================================================
                charArray[i] = 0x20;
            }
            else if (charArray[i] == 0x301c || charArray[i] == 0x2dc) {
                // =============================================================
                // 전자물결(0x301c)은 반자 물결(0x2dc)로 변경한다.
                // =============================================================
                charArray[i] = 0x7e;
            }
            else if (charArray[i] == 0x30fb) {
                charArray[i] = 0x318d;
            }
            else if (charArray[i] == 0x00) {
                // =============================================================
                // 널값(0x00)은 반자 스페이스(0x20)로 변경한다.
                // =============================================================
                charArray[i] = 0x20;
            }
        }

        ascii = rtrim(new String(charArray));

        return ascii;
    }

    /**
     * Right Trim을 한다.
     * 
     * @param str
     * @return
     */
    public static String rtrim(String str) {

        if (str == null || "".equals(str)) return "";

        String result = str;
        String blank = str.substring(str.length() - 1, str.length());
        if (" ".equals(blank)) {
            int sub = 0;
            char[] charArr = str.toCharArray();
            int len = str.toCharArray().length;
            char[] tobe = null;
            for (int idx=len-1; idx>=0; idx--) {
                if (charArr[idx] != 0x20) {
                    break;
                }
                else {
                    charArr[idx] = 0x00;
                    sub++;
                }
            }

            int lastIdx = len - sub;
            tobe = new char[lastIdx];
            
            for (int i=0; i<lastIdx; i++) {
                tobe[i] = charArr[i];
            }
            
            result = new String(tobe);
        }
        return result;
    }

    /**
     * 문자열을 입력받아 byte로 변환후에 substring한다음 다시 String으로 return해주는 메소드 (한글이 포함된 항목에 대한 substring시에 사용)
     * @param str
     * @param sLoc
     * @param eLoc
     * @return
     */
    public static String getByteToStr(String str, int sLoc, int eLoc) {
        byte[] byteStr;
        String cutStr = "";
        try {
            byteStr = str.getBytes();
            cutStr = new String(byteStr, sLoc, eLoc - sLoc);
        } catch (Exception e) {
        }

        return cutStr;
    }

    /**
     * 문자열길이 - 전반자 혼용시 서버에서 호스트로 배포시의 사이즈오류가 발생하는 건에 대해 사전검증차원에서 사용한다.
     * 
     * @param halfString
     * @return
     */
    public static int getHostLength(String halfString) {
        
        byte[] bytes = halfString.getBytes();
        int len=0;
        boolean isFirst = true;
        String status = "+";
        int mixedFullChar = 0;
        int mixedHalfChar = 0;
        for (byte bb : bytes) {
            if (isFirst) {
                if (bb < 0) {
                    status = "-";
                    len++; // 첫번째부터 전자이면 'SO'가 추가되므로 1Byte Add
                    isFirst = false;
                    mixedFullChar++;
                } else {
                    mixedHalfChar++;
                }
                len++; // 원래 읽은 글자 1Byte Add
            } else {
                if (bb < 0) {
                    if ("+".equals(status)) {
                        len++; // 전자가 시작되면 'SO'가 추가되므로 1Byte Add
                    }
                    len++; // 원래 읽은 글자 1Byte Add
                    status = "-";
                    mixedFullChar++;
                } else {
                    if ("-".equals(status)) {
                        len++; // 전자가 종료되면 'SI'가 추가되므로 1Byte Add
                    }
                    len++; // 원래 읽은 글자 1Byte Add
                    status = "+";
                    mixedHalfChar++;
                }
            }
        }

        // 모두전자이면 마지막에 'SI'가 추가되므로 1Byte Add
        if (mixedHalfChar > 0 && mixedHalfChar == 0) {
            len++;
        }

        return len;
    }
    
    /**
     * Map에서 특정키의 값을 가져옴
     *  - 해당하는 키가 없으면 null 이 아닌 빈스트링을 return
     *  
     * @param map
     * @param key
     * @return
     */
    public static String getData(Map<String,String> map, String key) {
    	if (map.containsKey(key)) return map.get(key);
    	else return "";
    }
    
    /**
     * 디렉토리 Path에 해당하는 file을  return.
     * Path가 없으면 root부터 시작하여 모든 하위 path를 생성한다.
     * 
     * @param dir
     * @return File
     */
    public static File getDirectoryFile(String dir) {
    	
    	File fDir	= new File(dir);
        
    	//파일업로드 디렉토리가 존재하지 않으면 생성
    	if (!fDir.exists()) {
    		String	lastParent	= "";
    		
    		//하위 Path List
    		List<String> listPath = new ArrayList<String>();
    		listPath.add(dir);	//최종 Path
    		while(lastParent != null) {
    			lastParent = fDir.getParent();
    			if (lastParent != null) {
    				listPath.add(lastParent);
    				fDir = new File(lastParent);
    			}
    		};
    		
    		
    		
    		//Directory 생성
    		int pathSize = listPath.size();
    		for (int i=pathSize-1; i >= 0; i--) {
    			File f = new File(listPath.get(i)); 
    			if (f.exists() == false) {
    				f.mkdir();
    			}
    		}
    		
    		//2. 최종 Directory 생성
    		fDir = new File(dir);
    	}
    	
    	return fDir;
    }
    
    /**
	 * Object가 Null일 경우 True
	 * 
	 * @param object
	 * @return {@link Boolean}
	 * @author Ji Hoon, OH(oh_jh@btbsolution.co.kr)
	 */
	public static boolean isNull(Object object) {
		if (object == null) {
			return true;
		}
	
		if (object instanceof String) {
			String str = (String) object;
			return str.length() == 0;
		}
	
		if (object instanceof Collection<?>) {
			Collection<?> collection = (Collection<?>) object;
			return collection.size() == 0;
		}
	
		if (object.getClass().isArray()) {
			try {
				if (Array.getLength(object) == 0) {
					return true;
				}
			} catch (Exception e) {
				// do nothing
			}
		}
	
		return false;
	}
}
