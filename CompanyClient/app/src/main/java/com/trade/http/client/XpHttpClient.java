package com.trade.http.client;


import android.text.TextUtils;

import com.trade.http.api.OkHttpApi;
import com.trade.http.request.XpRequest;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author yao.guoju
 * 为什么用枚举
 */
public enum XpHttpClient implements BaseClient {
	CLIENT;

	private final static int start = 100;
	private final static int end   = 20000;
	private InputStream certificate;
	private AtomicInteger mTag = new AtomicInteger(start);

	public int getCbTag(){
		int tag = mTag.incrementAndGet();
	    if(tag== end) {
	    	mTag.set(start);
	    }
	    return tag;
	}

	public void addCertificate(InputStream cert) {
		this.certificate = cert;
	}

	public InputStream getCertificate() {
		return certificate;
	}


	@Override
	public void newCall(XpRequest req, Callback cb, boolean rspOnUi) {
		if(!rspOnUi) {
			if(cb != null) {
				cb.addTag(getCbTag());
			}
			String method = req.getMethod();
			boolean isTls = req.getUrl().startsWith("https");
			if(!TextUtils.isEmpty(method)) {
				if("POST".equalsIgnoreCase(method)) {
					if(isTls) {
						OkHttpApi.HTTPS.post(req, cb,rspOnUi);
					}else {
						OkHttpApi.HTTP.post(req,cb,rspOnUi);
					}
				}else if("GET".equalsIgnoreCase(method)) {
					if(isTls) {
						OkHttpApi.HTTPS.get(req, cb,rspOnUi);
					}else {
						OkHttpApi.HTTP.get(req, cb,rspOnUi);
					}
				} else if ("PUT".equalsIgnoreCase(method)) {
					if(isTls) {
						OkHttpApi.HTTPS.put(req, cb,rspOnUi);
					}else {
						OkHttpApi.HTTP.put(req, cb,rspOnUi);
					}
				}
			}else {
				if(isTls) {
					OkHttpApi.HTTPS.get(req, cb,rspOnUi);
				}else {
					OkHttpApi.HTTP.get(req, cb,rspOnUi);
				}
			}
		}else {
			newCall(req,cb);
		}
	}

	@Override
	public void newCall(XpRequest req, Callback cb) {
		// TODO Auto-generated method stub
		if(cb != null) {
			cb.addTag(getCbTag());
		}
		String method = req.getMethod();
		boolean isTls = req.getUrl().startsWith("https");
		if(!TextUtils.isEmpty(method)) {
			if("POST".equalsIgnoreCase(method)) {
				if(isTls) {
					OkHttpApi.HTTPS.post(req, cb,true);
				}else {
					OkHttpApi.HTTP.post(req,cb,true);
				}
			}else if("GET".equalsIgnoreCase(method)) {
				if(isTls) {
					OkHttpApi.HTTPS.get(req, cb,true);
				}else {
					OkHttpApi.HTTP.get(req, cb,true);
				}
			} else if ("PUT".equalsIgnoreCase(method)) {
				if(isTls) {
					OkHttpApi.HTTPS.put(req, cb,true);
				}else {
					OkHttpApi.HTTP.put(req, cb,true);
				}
			}else if ("DELETE".equalsIgnoreCase(method)) {
				if(isTls) {
					OkHttpApi.HTTPS.delete(req, cb);
				}else {
					OkHttpApi.HTTP.delete(req, cb);
				}
			}
		}else {
			if(isTls) {
				OkHttpApi.HTTPS.get(req, cb,true);
			}else {
				OkHttpApi.HTTP.get(req, cb,true);
			}
		}
	}

	
	
}
