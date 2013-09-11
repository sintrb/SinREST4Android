/**
 * 
 */
package com.sin.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * ResourceAccess <br/>
 * 
 * @author RobinTang {@link https://github.com/sintrb/SinREST4Android}
 */
public class ResourceAccess {
	private static DefaultHttpClient httpClient = new DefaultHttpClient();
	private static final int HTTP_STATUSCODE_OK = 200;
	private HttpUriRequest request;
	private WebResource webResource;

	public ResourceAccess(WebResource webResource, HttpUriRequest request) {
		super();
		this.webResource = webResource;
		this.request = request;
	}

	public HttpUriRequest getRequest() {
		return request;
	}

	public String read() {
		String result = null;
		try {
			HttpResponse httpResp = httpClient.execute(request);
			if (httpResp.getStatusLine().getStatusCode() == HTTP_STATUSCODE_OK) {
				result = EntityUtils.toString(httpResp.getEntity(), webResource.getEncode());
				System.out.println("方式请求成功，返回数据如下：\n" + result);
			} else {
				System.err.println("方式请求失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	static List<NameValuePair> keyValueToValuePairList(Map<String, String> paramsMap) {
		final List<NameValuePair> dataList = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : paramsMap.entrySet()) {
			dataList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return dataList;
	}
}
