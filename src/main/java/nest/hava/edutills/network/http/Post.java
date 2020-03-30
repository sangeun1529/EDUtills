package nest.hava.edutills.network.http;


import nest.hava.edutills.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Post {
	
	int connectTimeout=5000;//second
	int readTimeout=10000;//second
	String keyCharset = "UTF-8";
	String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36";
	String testUrl;
	String url;
	int pageSize;
	private boolean formData = true;
	private String bodyString = "";
	Map<String,String> param = new HashMap<>();
	
	private Post(){}
	
	public String getTestUrl() {
		return testUrl;
	}

	public void setTestUrl(String testUrl) {
		this.testUrl = testUrl;
	}

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		
		if(StringUtils.isNotNullOrEmpty(url))
		{
			this.url = url.trim();
		}
		
	}

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
	
	public boolean isFormData() {
		return formData;
	}

	public void setFormData(boolean formData) {
		this.formData = formData;
	}
	
	public String getBodyString() {
		return bodyString;
	}

	public void setBodyString(String bodyString) {
		this.bodyString = bodyString;
	}

	public void addParam(String key, String value)
	{
		param.put(key, value);
	}
	
	public void removeParam(String key)
	{
		param.remove(key);
	}
	
	public String getValue(String key)
	{
		return param.get(key);
	}
	
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public List<String> getKeyList()
	{
		List<String> keyList = new ArrayList<>();
		param.keySet().forEach(k -> keyList.add(k));
		
		return keyList;
	}
	
	public static Post make(String url, int pageSize)
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
	public static Post make(String url, String testUrl, int pageSize)
	{
		Post pp = new Post();
		
		pp.setTestUrl(testUrl);
		pp.setUrl(url);
		pp.setPageSize(pageSize);
		
		return pp;
	}
	
	
	public static Post make(String url, int pageSize, int readTimeout)
	{
		return make(url, "", pageSize, readTimeout);
	}
	
	/**
	 * 
	 * @param url
	 * @param testUrl -> 필요없음
	 * @param pageSize
	 * @param readTimeout
	 * @return
	 */
	@Deprecated
	public static Post make(String url, String testUrl, int pageSize, int readTimeout)
	{
		Post pp = new Post();
		
		pp.setTestUrl(testUrl);
		pp.setUrl(url);
		pp.setPageSize(pageSize);
		pp.setReadTimeout(readTimeout);
		
		return pp;
	}
	
	public Post copy()
	{
		Post tt = new Post();
		
		tt.setConnectTimeout(connectTimeout);
		tt.setKeyCharset(keyCharset);
		tt.setPageSize(pageSize);
		tt.setReadTimeout(readTimeout);
		tt.setTestUrl(testUrl);
		tt.setUrl(url);
		tt.setUSER_AGENT(USER_AGENT);
		tt.setFormData(formData);
		tt.setBodyString(bodyString);
		param.keySet().forEach(x -> tt.addParam(x, param.get(x)));
		
		return tt;
	}
}
