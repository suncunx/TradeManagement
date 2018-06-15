package com.trade.other.finance.model;

import com.trade.model.FinanceResultBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Stephen Sun on 2017/8/16 0016.
 * Email:suncunx@qq.com
 */

public interface FinanceService {
    @FormUrlEncoded
    @POST("list.finance")
    Observable<FinanceResultBean> getFinances(@Field("year") String year);

//    @FormUrlEncoded
//    @POST("save.outBill")
//    Observable<SimpleResultBean> saveOutBill(@FieldMap Map<String, String> map);
//
//    @FormUrlEncoded
//    @POST("update.outBill")
//    Observable<SimpleResultBean> updateOutBill(@FieldMap Map<String, String> map);
}
