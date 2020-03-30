package nest.hava.edutills.network.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Get {
	
	transient int connectTimeout=5000;//second
	transient int readTimeout=10000;//second
	String keyCharset = "UTF-8";
	transient String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
	String testUrl;
	String url;
	int pageSize;
	Map<String,String> params = new HashMap<>();
	
	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public String getKeyCharset() {
		return keyCharset;
	}

	public void setKeyCharset(String keyCharset) {
		this.keyCharset = keyCharset;
	}

	public String getUSER_AGENT() {
		return USER_AGENT;
	}

	public void setUSER_AGENT(String uSER_AGENT) {
		USER_AGENT = uSER_AGENT;
	}

	public String getTestUrl() {
		return testUrl;
	}

	public void setTestUrl(String testUrl) {
		this.testUrl = testUrl.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url.trim();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Map<String, String> getParam() {
		return params;
	}

	
	public void addParam(String key, String value)
	{
		params.put(key, value);
	}
	
	public void removeParam(String key)
	{
		params.remove(key);
	}
	public List<String> getKeyList()
	{
		List<String> keyList = new ArrayList<>();
		params.keySet().forEach(k -> keyList.add(k));
		
		return keyList;
	}
	
	public String getValue(String key)
	{
		return params.get(key);
	}

	public static Get make(String url)
	{
		return make(url, "");
	}
	
	/**
	 * 
	 * <pre>
	 *  Description : HTTP의 GET 방식으로 요청하기 위한 METHOD
	 *  use {@link Get#make}
	 * </pre>
	 * @Method Name : make
	 * @date : 2017. 12. 15.
	 * @author : BaeMunHwan
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	Date				Writer						Content  
	 *	----------- ------------------- ---------------------------------------
	 *	2017. 12. 15.		BaeMunHwan				Initial Write
	 *	-----------------------------------------------------------------------
	 * 
	 * @param url
	 * @param testUrl
	 * @return
	 */
	@Deprecated
	public static Get make(String url, String testUrl)
	{
		Get pp = new Get();
		
		pp.setTestUrl(testUrl);
		pp.setUrl(url);
		
		return pp;
	}
	
	public static Get make(String url, Integer pageSize)
	{
		return make(url, "", pageSize);
	}
	
	/**
	 * 
	 * @param url
	 * @param testUrl -> 필요없음
	 * @param pageSize
	 * @return
	 */
	@Deprecated
	public static Get make(String url, String testUrl,Integer pageSize)
	{
		Get pp = new Get();
		
		pp.setTestUrl(testUrl);
		pp.setUrl(url);
		pp.setPageSize(pageSize);
		
		return pp;
	}
	public Get copy()
	{
		Get tt = new Get();
		tt.setConnectTimeout(connectTimeout);
		tt.setKeyCharset(keyCharset);
		tt.setReadTimeout(readTimeout);
		tt.setUrl(url);
		tt.setUSER_AGENT(USER_AGENT);
		tt.setPageSize(pageSize);
		
		params.keySet().forEach(x -> tt.addParam(x, params.get(x)));
		
		return tt;
	}

}
