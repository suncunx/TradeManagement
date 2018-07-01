package com.trade.login.model;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */

public interface LoginPwdService {

    @FormUrlEncoded
    @POST("login.login")
    Observable<LoginResultBean> login(@Field("phone") String phone, @Field("password") String password);

    @FormUrlEncoded
    @POST("loginDirect.login")
    Observable<LoginResultBean> login(@Field("phone") String phone);
}
