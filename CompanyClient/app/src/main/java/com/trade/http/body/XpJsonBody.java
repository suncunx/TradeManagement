package com.trade.http.body;

/**
 * 
 * @author yao.guoju
 *
 */
public class XpJsonBody extends XpBaseBody {
	private String json; 
	
	public XpJsonBody(String json) {
		// TODO Auto-generated constructor stub
		this.json = json;
	}
	
	@Override
	public String string() {
		// TODO Auto-generated method stub
		return json;
	}

}
