package nest.hava.edutills.util;

import nest.hava.edutills.match.MatchUtils;

import javax.print.URIException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URLUtils {

	/**
	 * 상대경로인지 아닌지를 판단해서 절대경로를 만들어준다
	 * 
	 * @param host
	 * @param url
	 * @return
	 */
	public static String addSchema(String host, String url) {
		if (url.toLowerCase().indexOf("http://") >= 0) {
			return url;
		}else if (url.toLowerCase().indexOf("https://") >= 0) {
			return url;
		}else {
			StringBuffer sb = new StringBuffer(host);
			sb.append(url);

			return sb.toString();
		}
	}
	
	
	public static boolean isSchemaAdded(String url)
	{
		if (url.toLowerCase().indexOf("http://") >= 0) {
			return true;
		} else if (url.toLowerCase().indexOf("https://") >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * host에 프로토콜이 없는 경우 프로토콜을 넣는다
	 * 
	 * @param host
	 * @return
	 */
	public static String addProtocol(String host, boolean ssl) {
		if (host.toLowerCase().indexOf("http://") >= 0) {
			return host;

		} else if (host.toLowerCase().indexOf("https://") >= 0) {
			return host;
			
		} else {

			StringBuffer sb = new StringBuffer();
			if (ssl) {
				if(host.startsWith("//"))
				{
					sb.append("https:");
					
				}else {
					sb.append("https://");
				}
				
			} else {
				if(host.startsWith("//"))
				{
					sb.append("http:");
					
				}else {
					sb.append("http://");
				}
				
			}
			sb.append(host);

			return sb.toString();
		}
	}

	public static String remove(String url) {
		url = MatchUtils.partialMatchAndRemove("\\n", url);

		return url;
	}

	/**
	 * query에 있는 변수와 주어진 변수가 같은지를 판단하여 같으면 해당 값을 전달한다.
	 * 
	 * @param uri
	 * @param key
	 * @return null
	 */
	public static String getQuery(URI uri, String key) {
		if (uri != null) {
			String query = uri.getQuery();

			String[] params = query.split("&");

			String[] keyValue;
			for (String p : params) {
				keyValue = p.split("=");

				if (StringUtils.defaultIfEmpty(keyValue[0], "").equals(key)) {
					return keyValue[1];
				}
			}
		}

		return null;
	}

	/**
	 * relace a regex pattern with a param in the url
	 * 
	 */
	public static String addParamToUrl(String url, String regex, String param) {
		return url.replaceAll(regex, param);
	}

	public static String getQueryString(Map<String, List<String>> form, String charset) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, List<String>> entry : form.entrySet()) {
			String name = entry.getKey();
			List<String> value = entry.getValue();
			for (String temp : value)
				sb.append("&").append(name + "=" + URLEncoder.encode(temp, charset));
		}

		String queryString = sb.toString();
		return queryString.length() > 0 ? queryString.substring(1) : "";
	}
	

	@SuppressWarnings("unchecked")
	public static Map<String, List<String>> getQueryMap(String url, String encoding) {
		String queryString = url.replaceAll("^.*\\?([^\\?]+)$", "$1");

		if (!StringUtils.isNullOrEmpty(queryString)) {
			try {

				Map<String, List<String>> map = new HashMap<>();

				String[] params = queryString.split("&");
				for (String param : params) {
					if (param.indexOf('=') > -1) {

						String name = param.substring(0, param.indexOf('='));
						String value = param.substring(param.indexOf('=') + 1);

						List<String> values;
						if (map.containsKey(name)) {
							values = map.get(name);
						} else {
							values = new ArrayList<>();
							map.put(name, values);
						}

						values.add(URLDecoder.decode(value, encoding));
					}
				}
				return map;
			} catch (Exception e) {
				throw new IllegalStateException(e.getMessage(), e);
			}
		}
		return Collections.EMPTY_MAP;
	}

	public static String getProtocolAndDomain(String url) {
		try {
			URI uri = new URI(url);
			return getProtocolAndDomain(uri);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("arg -> " + url);
		}
	}

	public static String getProtocolAndDomain(URI uri) {
		return String.format("%s://%s", uri.getScheme(), uri.getAuthority());
	}
}
