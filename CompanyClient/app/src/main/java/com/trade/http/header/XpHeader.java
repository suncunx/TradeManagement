package com.trade.http.header;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author yao.guoju
 */
public class XpHeader implements Serializable{
	
	private Map<String,String> headers;
	
	private XpHeader(Map<String,String> headers) {
		this.headers = headers;
	}
	
	public Map<String,String> getHeaders() {
		return headers;
	}
	
	public static class Builder {
		private Map<String,String> headers = new HashMap<String,String>();
		
		public Builder addHeader(String key,String value) {
			headers.put(key, value);
			return this;
		}
		
		public XpHeader build() {
			return new XpHeader(headers);
		}
	}
}
