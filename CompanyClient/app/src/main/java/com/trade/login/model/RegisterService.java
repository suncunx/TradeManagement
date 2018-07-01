package com.trade.login.model;


import com.trade.model.SimpleResultBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Stephen Sun on 2017/8/15 0015.
 * Email:suncunx@qq.com
 */

public interface RegisterService {

    @FormUrlEncoded
    @POST("register.login")
    Observable<SimpleResultBean> register(@Field("phone") String phone, @Field("password") String password);
}
