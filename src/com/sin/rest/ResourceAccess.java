package com.sin.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
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

	public ResourceAccess accept(String mediaType) {
		this.request.setHeader("Accept", mediaType);
		return this;
	}

	public ResourceAccess accept(MediaType mediaType) {
		return accept(mediaType.toString());
	}

	public ResourceAccess type(String mediaType) {
		this.request.setHeader("Content-Type", mediaType);
		return this;
	}

	public ResourceAccess type(MediaType mediaType) {
		return type(mediaType.toString());
	}

	public HttpEntity rawRead(HttpEntity entity) throws Exception {
		System.out.println("->" + request.getURI().toString());
		if (entity != null) {
			// POST Data
			((HttpEntityEnclosingRequest) request).setEntity(entity);
		}
		HttpResponse httpResp = httpClient.execute(request);

		if (httpResp.getStatusLine().getStatusCode() == HTTP_STATUSCODE_OK) {
			return httpResp.getEntity();
		} else {
			throw new Exception("Status Code Error: " + httpResp.getStatusLine().getStatusCode());
		}
	}

	public String read() {
		return read(null, null);
	}

	public String read(Map<String, String> form) {
		return read(form, null);
	}

	public String read(String text) {
		return read(null, text);
	}

	public String read(Map<String, String> form, String text) {
		HttpEntity resEntity = null;
		HttpEntity reqEntity = null;
		String res = null;
		try {
			if (form != null) {
				reqEntity = new UrlEncodedFormEntity(keyValueToValuePairList(form), webResource.getEncode());
			} else if (text != null) {
				reqEntity = new StringEntity(text, webResource.getEncode());
			}
			resEntity = rawRead(reqEntity);
			res = EntityUtils.toString(resEntity, webResource.getEncode());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			request.abort();
		}
		System.out.println(res == null ? "" : res);
		return res;
	}

	static private List<NameValuePair> keyValueToValuePairList(Map<String, String> paramsMap) {
		final List<NameValuePair> dataList = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : paramsMap.entrySet()) {
			dataList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return dataList;
	}
}
