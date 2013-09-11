package com.sin.rest.test;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;

import com.sin.rest.WebResource;

public class MainTest {

	private static void initHttps() {
		SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
	}

	public static void main(String[] args) {
		initHttps();
		new WebResource("https://test.rigbee.cn/oreservices/services").get("userservice/loginJson/zhouzhihao/1").read();
	}
}
