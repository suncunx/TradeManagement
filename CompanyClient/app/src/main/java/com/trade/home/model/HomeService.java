package com.trade.home.model;

import com.trade.model.SimpleResultBean;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Stephen Sun on 2017/8/16 0016.
 * Email:suncunx@qq.com
 */

public interface HomeService {

    @FormUrlEncoded
    @POST("list.outBill")
    Observable<OutBillResultBean> getOutBills(@Field("pageNo") int pageNo);

    @FormUrlEncoded
    @POST("save.outBill")
    Observable<SimpleResultBean> saveOutBill(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("update.outBill")
    Observable<SimpleResultBean> updateOutBill(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("list.inBill")
    Observable<InBillResultBean> getInBills(@Field("pageNo") int pageNo);

    @FormUrlEncoded
    @POST("save.inBill")
    Observable<SimpleResultBean> saveInBill(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("update.inBill")
    Observable<SimpleResultBean> updateInBill(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("save.supplier")
    Observable<SimpleResultBean> saveSupplier(@FieldMap Map<String, String> map);
}
