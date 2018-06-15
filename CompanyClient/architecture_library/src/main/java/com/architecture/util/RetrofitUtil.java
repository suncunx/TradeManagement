package com.architecture.util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RetrofitUtil {
    public static OkHttpClient genericClient(final String token) {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Request.Builder builder1 = request.newBuilder();
                        Request build = builder1.addHeader("token", token).build();
                        return chain.proceed(build);
                    }
                }).retryOnConnectionFailure(true)
                .build();
    }
}