package com.sin.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

/**
 * REST调用工具类 参考： http://zh.wikipedia.org/wiki/REST
 * http://www.ideasandroid.com/archives/429
 * 
 * @author Bruce
 * 
 */
public final class RestUtil {
	private static final String DEFAULT_LOCAL_ENCODE = "UTF-8";
	private static final String DEFAULT_REMOTE_ENCODE = "UTF-8";
	private static final int HTTP_200 = 200;
	private static DefaultHttpClient httpClient = new DefaultHttpClient();
	static {
		// 设置timeout
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT, Integer.valueOf(5000));
		httpClient.getParams().setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, Integer.valueOf(3000));
	}

	/**
	 * 发送http请求
	 * 
	 * @param methodEnum
	 *            请求的方法
	 * @param url
	 *            地址
	 * @param paramsMap
	 *            参数信息（可为空）
	 * @return
	 */
	public static String sendRequest(HttpRequestMethod methodEnum, String url, Map<String, String> paramsMap) {
		String result = null;
		HttpUriRequest req = methodEnum.createRequest(url);
//		req.setHeader("Accept", "xml");
		try {
			if (paramsMap != null) {
				((HttpEntityEnclosingRequest) req).setEntity(new UrlEncodedFormEntity(keyValueToValuePairList(paramsMap), DEFAULT_REMOTE_ENCODE));
			}
			HttpResponse httpResp = httpClient.execute(req);

			if (httpResp.getStatusLine().getStatusCode() == HTTP_200) {
				result = EntityUtils.toString(httpResp.getEntity(), DEFAULT_LOCAL_ENCODE);
				System.out.println(methodEnum + "方式请求成功，返回数据如下：" + result);
			} else {
				System.err.println(methodEnum + "方式请求失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将键值对转换成NameValuePair列表
	 * 
	 * @param paramsMap
	 * @return
	 */
	static List<NameValuePair> keyValueToValuePairList(Map<String, String> paramsMap) {
		final List<NameValuePair> dataList = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : paramsMap.entrySet()) {
			dataList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return dataList;
	}
}