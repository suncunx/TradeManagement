package com.trade.http.response;

import java.io.Serializable;

/**
 * 
 * @author yao.guoju
 *
 */
public class XpResponse implements Serializable{
	private int code;
	private String response;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}
