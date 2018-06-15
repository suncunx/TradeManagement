package com.trade.goods.save.model;

import com.trade.model.SimpleResultBean;
import com.trade.model.SupplierResultBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Stephen Sun on 2017/8/16 0016.
 * Email:suncunx@qq.com
 */

public interface GoodsSaveService {
    @POST("list.supplier")
    Observable<SupplierResultBean> getSuppliers();

    @FormUrlEncoded
    @POST("save.goods")
    Observable<SimpleResultBean> saveGoods(@FieldMap Map<String, String> map);
}
