package com.trade.http.request;

import com.trade.http.body.XpBaseBody;
import com.trade.http.header.XpHeader;

import java.io.Serializable;

/**
 * 
 * @author yao.guoju
 */
public class XpRequest implements Serializable{
	public XpHeader getHeader() {
		return header;
	}

	public String getUrl() {
		return url;
	}

	public XpBaseBody getBody() {
		return body;
	}

	public String getMethod() {
		return method;
	}

	private XpHeader header;
	private String   url;
	private XpBaseBody body;
	private String  method;
	
	private XpRequest(XpHeader header, String url, XpBaseBody body, String method) {
		this.header = header;
		this.url =  url;
		this.body = body;
		this.method = method;
	}
	
	public static class Builder {
		
		private XpHeader header;
		private String url;
		private XpBaseBody body;
		private String method;
		
		public Builder header(XpHeader header) {
			this.header = header;
			return this;
		}
		
		public Builder url(String url) {
			this.url = url;
			return this;
		}
		
		public Builder method(String method) {
			this.method = method;
			return this;
		}
		
		public Builder body(XpBaseBody body) {
			this.body = body;
			return this;
		}
		
		public XpRequest build() {
			return new XpRequest(header, url, body,method);
		}
	}
}
