package com.trade.http.client;


import com.trade.http.response.XpResponse;

/**
 * 
 * @author yao.guoju
 *
 */
public abstract class Callback {
	private int tag;
	public abstract void onResponse(XpResponse rsp) throws Exception;
	public abstract void onFailure();
	
	protected void addTag(int t) {
		tag = t;
	}
	
	public int getTag() {
		return tag;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return tag;
	}
	
}
