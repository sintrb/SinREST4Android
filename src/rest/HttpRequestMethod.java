package com.sin.rest;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

public enum HttpRequestMethod {
	HttpGet {
		@Override
		public HttpUriRequest createRequest(String url) {
			return new HttpGet(url);
		}
	},
	HttpPost {
		@Override
		public HttpUriRequest createRequest(String url) {
			return new HttpPost(url);
		}
	},
	HttpPut {
		@Override
		public HttpUriRequest createRequest(String url) {
			return new HttpPut(url);
		}
	},
	HttpDelete {
		@Override
		public HttpUriRequest createRequest(String url) {
			return new HttpDelete(url);
		}
	};

	public HttpUriRequest createRequest(String url) {
		return null;
	}
}