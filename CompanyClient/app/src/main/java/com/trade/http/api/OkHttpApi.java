package com.trade.http.api;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.trade.http.body.XpBaseBody;
import com.trade.http.body.XpJsonBody;
import com.trade.http.client.Callback;
import com.trade.http.client.XpHttpClient;
import com.trade.http.header.XpHeader;
import com.trade.http.request.XpRequest;
import com.trade.http.response.XpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author yao.guoju
 * @hide
 */
public enum OkHttpApi {
    HTTP,
    HTTPS;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;
    private static final int CALLBACK_OK = 0;
    private static final int CALLBACK_FAIL = 1;

    private Map<Integer, Callback> callbacks = new ConcurrentHashMap<>();

    private Handler handler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CALLBACK_OK:
                    XpResponse rsp = (XpResponse) msg.obj;
                    com.trade.http.client.Callback cbok = callbacks.get(msg.arg1);
                    if (cbok != null) {
                        try {
                            cbok.onResponse(rsp);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        callbacks.remove(cbok.getTag());
                    }
                    break;
                case CALLBACK_FAIL:
                    com.trade.http.client.Callback cbfail = callbacks.get(msg.arg1);
                    if (cbfail != null) {
                        cbfail.onFailure();
                        callbacks.remove(cbfail.getTag());
                    }
                    break;
                default:
                    break;
            }
        }

    };


    OkHttpApi() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if ("HTTPS".equals(name())) {
//			ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
//					.allEnabledTlsVersions()
//					.allEnabledCipherSuites()
//					.build();
//			clientBuilder.connectionSpecs(Collections.singletonList(spec));
            X509TrustManager trustManager = null;
            if (XpHttpClient.CLIENT.getCertificate() == null) {
                trustManager = new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                };
            } else {
                try {
                    trustManager = trustManagerForCertificates(XpHttpClient.CLIENT.getCertificate());
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
            }

            SSLSocketFactory sslSocketFactory = null;
            try {
                SSLContext sc = SSLContext.getInstance("TLS");
                sc.init(null, new TrustManager[]{trustManager}, new SecureRandom());
                sslSocketFactory = sc.getSocketFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (sslSocketFactory != null) {
                clientBuilder.sslSocketFactory(sslSocketFactory);
            }
            clientBuilder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
        }
        client = clientBuilder.build();
    }

    private X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }

        // Put the certificates a key store.
        char[] password = "password".toCharArray(); // Any password will work.
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }

        // Use it to build an X509 trust manager.
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    private KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Get
     *
     * @param req
     * @param cb
     */
    public void get(XpRequest req, final Callback cb) {
        Request.Builder reqBuilder = new Request.Builder();
        String url = req.getUrl();
        if (TextUtils.isEmpty(url)) {
            cb.onFailure();
            return;
        }
        reqBuilder.url(url);

        XpHeader header = req.getHeader();
        if (header != null) {
            Map<String, String> headers = header.getHeaders();
            if (headers.size() > 0) {
                Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    reqBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }

        client.newCall(reqBuilder.build()).enqueue(new okhttp3.Callback() {

            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                // TODO Auto-generated method stub
                XpResponse rsp = new XpResponse();
                rsp.setCode(response.code());
                rsp.setResponse(response.body().string());
                callbackOk(cb, rsp);
            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                // TODO Auto-generated method stub
                arg1.printStackTrace();
                callbackFailed(cb);
            }
        });
    }

    private void callbackOk(Callback cb, XpResponse rsp) {
        callbacks.put(cb.getTag(), cb);
        Message msg = Message.obtain();
        msg.what = CALLBACK_OK;
        msg.arg1 = cb.getTag();
        msg.obj = rsp;
        handler.sendMessage(msg);
    }

    private void callbackFailed(Callback cb) {
        callbacks.put(cb.getTag(), cb);
        Message msg = Message.obtain();
        msg.what = CALLBACK_FAIL;
        msg.arg1 = cb.getTag();
        handler.sendMessage(msg);
    }

    /**
     * Post
     *
     * @param req
     * @param cb
     */
    public void post(XpRequest req, final Callback cb) {
        Request.Builder reqBuilder = new Request.Builder();
        String url = req.getUrl();
        if (TextUtils.isEmpty(url)) {
            cb.onFailure();
            return;
        }
        reqBuilder.url(url);
        XpHeader header = req.getHeader();
        if (header != null) {
            Map<String, String> headers = header.getHeaders();
            if (headers.size() > 0) {
                Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    reqBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }

        XpBaseBody body = req.getBody();
        if (body instanceof XpJsonBody) {
            XpJsonBody jsonbody = (XpJsonBody) body;
            RequestBody b = RequestBody.create(JSON, jsonbody.string());
            reqBuilder.post(b);
        }
        client.newCall(reqBuilder.build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                // TODO Auto-generated method stub
                XpResponse rsp = new XpResponse();
                rsp.setCode(response.code());
                rsp.setResponse(response.body().string());
                callbackOk(cb, rsp);
            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                // TODO Auto-generated method stub
                arg1.printStackTrace();
                callbackFailed(cb);
            }
        });
    }

    /**
     * Put
     *
     * @param req
     * @param cb
     */
    public void put(XpRequest req, final Callback cb) {
        Request.Builder reqBuilder = new Request.Builder();
        String url = req.getUrl();
        if (TextUtils.isEmpty(url)) {
            cb.onFailure();
            return;
        }
        reqBuilder.url(url);
        XpHeader header = req.getHeader();
        if (header != null) {
            Map<String, String> headers = header.getHeaders();
            if (headers.size() > 0) {
                Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    reqBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }

        XpBaseBody body = req.getBody();
        if (body instanceof XpJsonBody) {
            XpJsonBody jsonbody = (XpJsonBody) body;
            RequestBody b = RequestBody.create(JSON, jsonbody.string());
            reqBuilder.put(b);
        }

        client.newCall(reqBuilder.build()).enqueue(new okhttp3.Callback() {

            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                // TODO Auto-generated method stub
                XpResponse rsp = new XpResponse();
                rsp.setCode(response.code());
                rsp.setResponse(response.body().string());
                callbackOk(cb, rsp);
            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                // TODO Auto-generated method stub
                arg1.printStackTrace();
                callbackFailed(cb);
            }
        });
    }


    public void delete(XpRequest req, final Callback cb) {
        Request.Builder reqBuilder = new Request.Builder();
        String url = req.getUrl();
        if (TextUtils.isEmpty(url)) {
            cb.onFailure();
            return;
        }
        reqBuilder.url(url);
        XpHeader header = req.getHeader();
        if (header != null) {
            Map<String, String> headers = header.getHeaders();
            if (headers.size() > 0) {
                Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    reqBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }

        XpBaseBody body = req.getBody();
        if (body instanceof XpJsonBody) {
            XpJsonBody jsonbody = (XpJsonBody) body;
            RequestBody b = RequestBody.create(JSON, jsonbody.string());
            reqBuilder.delete(b);
        }
        client.newCall(reqBuilder.build()).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(Call arg0, Response response) throws IOException {
                // TODO Auto-generated method stub
                XpResponse rsp = new XpResponse();
                rsp.setCode(response.code());
                rsp.setResponse(response.body().string());
                callbackOk(cb, rsp);
            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                // TODO Auto-generated method stub
                arg1.printStackTrace();
                callbackFailed(cb);
            }
        });
    }


	/**
	 * Get
	 * @param req
	 * @param cb
	 *
	 */
	public void get(XpRequest req, final Callback cb, final boolean rspOnUi) {
		Request.Builder reqBuilder = new Request.Builder();
		String url = req.getUrl();
		if(TextUtils.isEmpty(url)) {
			cb.onFailure();
			return ;
		}
		reqBuilder.url(url);

		XpHeader header = req.getHeader();
		if(header != null ) {
			Map<String, String> headers = header.getHeaders();
			if(headers.size() > 0) {
				Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
				while(iterator.hasNext()) {
					Map.Entry<String, String> entry = iterator.next();
					reqBuilder.addHeader(entry.getKey(), entry.getValue());
				}
			}
		}

		client.newCall(reqBuilder.build()).enqueue(new okhttp3.Callback() {

			@Override
			public void onResponse(Call arg0, Response response) throws IOException {
				// TODO Auto-generated method stub
				XpResponse rsp = new XpResponse();
				rsp.setCode(response.code());
				rsp.setResponse(response.body().string());
				if(rspOnUi) {
					callbackOk(cb, rsp);
				}else {
					try {
						cb.onResponse(rsp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call arg0, IOException arg1) {
				// TODO Auto-generated method stub
				arg1.printStackTrace();
				if(rspOnUi) {
					callbackFailed(cb);
				}else {
					cb.onFailure();
				}
			}
		});
	}

	/**
	 * Post
	 * @param req
	 * @param cb
	 */
	public  void post(XpRequest req, final Callback cb, final boolean rspOnUi) {
		Request.Builder reqBuilder = new Request.Builder();
		String url = req.getUrl();
		if(TextUtils.isEmpty(url)) {
			cb.onFailure();
			return ;
		}
		reqBuilder.url(url);
		XpHeader header = req.getHeader();
		if(header != null ) {
			Map<String, String> headers = header.getHeaders();
			if(headers.size() > 0) {
				Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
				while(iterator.hasNext()) {
					Map.Entry<String, String> entry = iterator.next();
					reqBuilder.addHeader(entry.getKey(), entry.getValue());
				}
			}
		}

		XpBaseBody body = req.getBody();
		if(body instanceof XpJsonBody) {
			  XpJsonBody jsonbody = (XpJsonBody)body;
			  RequestBody b = RequestBody.create(JSON, jsonbody.string());
			  reqBuilder.post(b);
		}
		client.newCall(reqBuilder.build()).enqueue(new okhttp3.Callback() {
			@Override
			public void onResponse(Call arg0, Response response) throws IOException {
				// TODO Auto-generated method stub
				XpResponse rsp = new XpResponse();
				rsp.setCode(response.code());
				rsp.setResponse(response.body().string());
				if(rspOnUi) {
					callbackOk(cb, rsp);
				}else {
					try {
						cb.onResponse(rsp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call arg0, IOException arg1) {
				// TODO Auto-generated method stub
				arg1.printStackTrace();
				if(rspOnUi) {
					callbackFailed(cb);
				}else {
					cb.onFailure();
				}
			}
		});
	}

	/**
	 * Put
	 * @param req
	 * @param cb
	 */
	public void put(XpRequest req, final Callback cb, final boolean rspOnUi) {
		Request.Builder reqBuilder = new Request.Builder();
		String url = req.getUrl();
		if(TextUtils.isEmpty(url)) {
			cb.onFailure();
			return ;
		}
		reqBuilder.url(url);
		XpHeader header = req.getHeader();
		if(header != null ) {
			Map<String, String> headers = header.getHeaders();
			if(headers.size() > 0) {
				Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
				while(iterator.hasNext()) {
					Map.Entry<String, String> entry = iterator.next();
					reqBuilder.addHeader(entry.getKey(), entry.getValue());
				}
			}
		}

		XpBaseBody body = req.getBody();
		if(body instanceof XpJsonBody) {
			XpJsonBody jsonbody = (XpJsonBody)body;
			RequestBody b = RequestBody.create(JSON, jsonbody.string());
			reqBuilder.put(b);
		}

		client.newCall(reqBuilder.build()).enqueue(new okhttp3.Callback() {

			@Override
			public void onResponse(Call arg0, Response response) throws IOException {
				// TODO Auto-generated method stub
				XpResponse rsp = new XpResponse();
				rsp.setCode(response.code());
				rsp.setResponse(response.body().string());
				if(rspOnUi) {
					callbackOk(cb, rsp);
				}else {
					try {
						cb.onResponse(rsp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Call arg0, IOException arg1) {
				// TODO Auto-generated method stub
				arg1.printStackTrace();
				if(rspOnUi) {
					callbackFailed(cb);
				}else {
					cb.onFailure();
				}
			}
		});
	}
}
