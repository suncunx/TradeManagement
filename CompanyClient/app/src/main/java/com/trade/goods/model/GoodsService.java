package com.trade.goods.model;

import com.trade.model.SimpleResultBean;
import com.trade.other.model.SupplierResultBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Stephen Sun on 2017/8/16 0016.
 * Email:suncunx@qq.com
 */

public interface GoodsService {
    @POST("list.goods")
    Observable<GoodsResultBean> getGoodss();

    @FormUrlEncoded
    @POST("remove.goods")
    Observable<SimpleResultBean> removeGoods(@Field("goodsId") String goodsId, @Field("supplierId") String supplierId);

    @POST("list.supplier")
    Observable<SupplierResultBean> getSuppliers();
}
