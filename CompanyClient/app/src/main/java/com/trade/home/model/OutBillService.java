package com.trade.home.model;

import com.trade.other.model.DeliverResultBean;
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

public interface OutBillService {
    @POST("list.deliver")
    Observable<DeliverResultBean> getDelivers();
//
//    @FormUrlEncoded
//    @POST("save.outBill")
//    Observable<SimpleResultBean> saveOutBill(@FieldMap Map<String, String> map);
//
    @FormUrlEncoded
    @POST("update.outBill")
    Observable<SimpleResultBean> updateOutBill(@FieldMap Map<String, String> map);
}
