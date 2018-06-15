package com.trade.deliver;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface DeliverService {

    @FormUrlEncoded
    @POST("login.deliver")
    Observable<SimpleResultBean> login(@Field("phone") String phone, @Field("password") String password);

    @FormUrlEncoded
    @POST("mission.deliver")
    Observable<OutBillDetailResultBean> mission(@Field("deliverId") String deliverId);


    @FormUrlEncoded
    @POST("detail.outBill")
    Observable<OutBillResultBean> detailOutBill(@Field("userId") String userId, @Field("outBillId") String outBillId);
}
