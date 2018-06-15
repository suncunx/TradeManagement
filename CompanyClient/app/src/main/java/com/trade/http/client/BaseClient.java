package com.trade.http.client;


import com.trade.http.request.XpRequest;

/**
 * 
 * @author yao.guoju
 *
 */
public interface  BaseClient {
	 void newCall(XpRequest req, Callback cb);
	 void newCall(XpRequest req, Callback cb, boolean rspOnUi);
}
