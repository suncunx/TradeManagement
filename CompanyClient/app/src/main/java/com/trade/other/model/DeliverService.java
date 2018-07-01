package com.trade.other.model;

import com.trade.model.SimpleResultBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Stephen Sun on 2017/8/16 0016.
 * Email:suncunx@qq.com
 */

public interface DeliverService {
    @POST("list.deliver")
    Observable<DeliverResultBean> getDelivers();

    @FormUrlEncoded
    @POST("save.deliver")
    Observable<SimpleResultBean> saveDeliver(@FieldMap Map<String, String> map);

//    @FormUrlEncoded
//    @POST("update.supplier")
//    Observable<SimpleResultBean> updateSupplier(@FieldMap Map<String, String> map);
//
//    @FormUrlEncoded
//    @POST("remove.supplier")
//    Observable<SimpleResultBean> removeSupplier(@Field("supplierId") String supplierId);
}
