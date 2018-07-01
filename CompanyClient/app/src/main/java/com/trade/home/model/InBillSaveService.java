package com.trade.home.model;

import com.trade.goods.model.GoodsResultBean;
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

public interface InBillSaveService {
    @POST("list.goods")
    Observable<GoodsResultBean> getGoods();

    @FormUrlEncoded
    @POST("save.inBill")
    Observable<SimpleResultBean> saveInBill(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("update.inBill")
    Observable<SimpleResultBean> updateInBill(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("goodsCount.repertory")
    Observable<SimpleResultBean> getRepertory(@Field("goodsId") String goodsId);
}
