package com.sin.rest;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

/**
 * WebResource <br/>
 * 
 * @author RobinTang {@link https://github.com/sintrb/SinREST4Android}
 */
public class WebResource {
	private String url;
	private String encode = "utf-8";

	public WebResource(String url) {
		this.url = url.endsWith("/") ? url : url + "/";
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getUrl() {
		return url;
	}

	
	public String getFullPath(String path) {
		return this.url + (path.startsWith("/") ? path.substring(1) : path);
	}

	public ResourceAccess get(String path) {
		return new ResourceAccess(this, new HttpGet(getFullPath(path)));
	}

	public ResourceAccess post(String path) {
		return new ResourceAccess(this, new HttpPost(getFullPath(path)));
	}

	public ResourceAccess delete(String path) {
		return new ResourceAccess(this, new HttpDelete(getFullPath(path)));
	}

	public ResourceAccess put(String path) {
		return new ResourceAccess(this, new HttpPut(getFullPath(path)));
	}
}
